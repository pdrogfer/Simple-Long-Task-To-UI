package com.pdrogfer.asynctasktoui;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pdro on 22/06/2016.
 */
public class MyAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "ASYNCTASK_TO_UI";

    Context context;
    private OnAsyncTaskCompleted asyncTaskListener;

    public MyAsyncTask(Context context, OnAsyncTaskCompleted asyncTaskListener) {
        this.context = context;
        this.asyncTaskListener = asyncTaskListener;
    }

    @Override
    protected void onPostExecute(String s) {
        asyncTaskListener.onAsyncTaskCompleted(s);
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String answerJsonStr = null;

        try {
            URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&q=eminem&type=video&key=AIzaSyBR9OFUSYfGpHwwFH3_IZ1Yvt1ryPNpUVM");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            answerJsonStr = buffer.toString();
            Log.i(TAG, "JSON obtained: " + answerJsonStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
        return answerJsonStr;
    }
}
