package com.pavanandroid.apps.tweetontheway;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "yes989UdUVeWnvJD0iaLnQ";       // Change this
    public static final String REST_CONSUMER_SECRET = "OpYPaiokcAJQwr6SmJOMmK9w05RQoWo1bn5Vbu3TNo"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://tweetontheway"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    //Gets the Home Timeline
    public void getHomeTimeline(AsyncHttpResponseHandler handler, int tweetCount){
    	String url = getApiUrl("statuses/home_timeline.json?count="+tweetCount);
    	client.get(url, null, handler);
    }
    
    //Post the status message
    public void postNewTweet(AsyncHttpResponseHandler handler, String newTweet){
    	String url = getApiUrl("statuses/update.json");
    	RequestParams params = new RequestParams ("status",newTweet);
    	client.post(url, params,handler);
    }
    
    
}