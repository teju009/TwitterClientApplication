package com.pavanandroid.apps.tweetontheway;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.pavanandroid.apps.tweetontheway.models.Tweet;

public class TimelineActivity extends Activity {

	int tweetCount=0;
	ListView lvTweets;
	TweetsAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		loadTweets();	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	
	private void loadTweets(){
		TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets){
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
				
				lvTweets = (ListView) findViewById(R.id.lvTweetsId);
				adapter = new TweetsAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
				
				lvTweets.setOnScrollListener(new EndlessScrollListener(){

					@Override
					public void onLoadMore(int page, int totalItemsCount) {
						tweetCount +=10;
						loadTweets();
					}
				});
				
			}
		}, tweetCount);
	}

	public boolean onAction(MenuItem mi){
		switch(mi.getItemId()){
		case R.id.composeItemId:
			composeTweet();
			return true;
		case R.id.refreshTimelineId:
			refreshTimeline();
			return true;
		default:
			return super.onOptionsItemSelected(mi);
		}
	}
	
	public void composeTweet(){
		Intent i = new Intent(this,ComposeActivity.class);
        startActivity(i); 
	}
	
	public void refreshTimeline(){
		tweetCount =0;
		loadTweets();
	}

}
