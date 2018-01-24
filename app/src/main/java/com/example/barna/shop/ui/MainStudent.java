package com.example.barna.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.R;

public class MainStudent extends BaseActivity implements View.OnClickListener {

    Button logout;
    Button showClasses;
    Button showGrades;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showClasses = (Button) findViewById(R.id.showClasses);
        showGrades = (Button) findViewById(R.id.showGrades);
        logout = (Button) findViewById(R.id.logout);


        showClasses.setOnClickListener(this);
        logout.setOnClickListener(this);


     }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.logout:
                startAsActivity(LoginActivity.class,true);
                break;
            case R.id.showClasses:
                startAsActivity(ShowStudentClassesActivity.class);
        }

    }
}
