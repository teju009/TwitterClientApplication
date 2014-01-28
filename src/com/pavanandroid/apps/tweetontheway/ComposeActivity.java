package com.pavanandroid.apps.tweetontheway;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposeActivity extends Activity {

	EditText etNewTweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	public void onSubmitTweet(View v){
		etNewTweet = (EditText) findViewById(R.id.newTweetId);
		String newTweet = etNewTweet.getText().toString();
		TwitterClientApp.getRestClient().postNewTweet(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonTweet) {
            	 Intent i = new Intent(getApplicationContext(),TimelineActivity.class);
                 startActivity(i);
            }
        }, newTweet);
	}

}
