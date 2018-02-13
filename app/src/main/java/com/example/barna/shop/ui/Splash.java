package com.example.barna.shop.ui;

import android.os.Handler;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.utils.StoreData;
import com.example.barna.shop.R;

public class Splash extends BaseActivity {

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
                while (mProgressStatus < 100) {
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
                        if (StoreData.s.getIsLoggedIn()){
                            startAsActivity(MainStudent.class,true);
                        }else if (StoreData.s.getIsLoggedInTeacher()){
                            startAsActivity(MainTeacher.class,true);
                        }else {
                            startAsActivity(LoginActivity.class, true);
                        }
                    }
                });
            }
        }).start();
        onStop();
    }
}
