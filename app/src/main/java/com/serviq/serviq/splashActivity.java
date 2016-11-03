package com.serviq.serviq;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.serviq.serviq.R.string.splash;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Progress progress = new Progress();
        progress.execute();
    }

    private class Progress extends AsyncTask<String, Integer, Void> {
        ProgressBar myProgress;

        @Override
        protected Void doInBackground(String... params) {
            try{
                for(int i=0; i < 100; i++) {
                    Thread.sleep(25);
                    publishProgress(i);
                }
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myProgress = (ProgressBar) findViewById(R.id.progressBar);
            myProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values[0]);
            myProgress.setProgress(values[0]);
        }
    }

}
