package com.pdrogfer.asynctasktoui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnLoopjCompleted, OnAsyncTaskCompleted {

    Button btnAsyncTask;
    Button btnLoopj;
    Button btnClear;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
        btnLoopj = (Button) findViewById(R.id.btnLoopj);
        btnClear = (Button) findViewById(R.id.btnClear);
        textView = (TextView) findViewById(R.id.tvResults);
        btnAsyncTask.setOnClickListener(this);
        btnLoopj.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }


    private void useAsyncTask() {
        new MyAsyncTask(this, this).execute();
    }

    private void useLoopj() {
        MyLoopjTask myLoopjTask = new MyLoopjTask(this, this);
        myLoopjTask.executeLoopjCall();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAsyncTask:
                useAsyncTask();
                break;
            case R.id.btnLoopj:
                useLoopj();
                break;
            case R.id.btnClear:
                textView.setText("results");
        }
    }

    @Override
    public void onLoopjCompleted(String s) {
        textView.setText(s);
    }

    @Override
    public void onAsyncTaskCompleted(String s) {
        textView.setText(s);
    }
}
