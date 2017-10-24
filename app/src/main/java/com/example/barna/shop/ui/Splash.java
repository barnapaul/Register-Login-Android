package com.example.barna.shop.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.barna.shop.utils.StoreData;
import com.example.barna.shop.R;

public class Splash extends AppCompatActivity {

    private ProgressBar mProgressBar;

    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StoreData.init(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100){
                    mProgressStatus++;
                    android.os.SystemClock.sleep(30);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent register = new Intent(Splash.this, LoginActivity.class);
                        startActivity(register);
                    }
                });
            }
        }).start();
        onStop();
    }
}
