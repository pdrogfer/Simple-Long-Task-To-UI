package com.pdrogfer.asynctasktoui;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by pdro on 22/06/2016.
 */
public class MyLoopjTask {

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    private OnLoopjCompleted loopjListener;
    String jsonResponse;
    Context context;

    public MyLoopjTask(Context context, OnLoopjCompleted loopjListener) {
        asyncHttpClient = new AsyncHttpClient();
        requestParams = new RequestParams();
        this.loopjListener = loopjListener;
        this.context = context;
    }

    public void executeLoopjCall() {
        requestParams.put("q", "madonna");
        requestParams.put("part", "snippet");
        requestParams.put("type", "video");
        requestParams.put("maxResults", 10);
        requestParams.put("key", "AIzaSyBR9OFUSYfGpHwwFH3_IZ1Yvt1ryPNpUVM");

        asyncHttpClient.get("https://www.googleapis.com/youtube/v3/search", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonResponse = response.toString();
                loopjListener.onLoopjCompleted(jsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void returnResponse(String jsonResponse) {

    }
}

