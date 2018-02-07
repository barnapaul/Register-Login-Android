package com.example.barna.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.utils.StoreData;


public class MainTeacher extends BaseActivity implements View.OnClickListener {

    Button showStudents;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        showStudents = (Button) findViewById(R.id.showStudents);
        logout = (Button) findViewById(R.id.logout);


        showStudents.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.logout:
                StoreData.s.isLoggedInTeacher(false);
                startAsActivity(LoginActivity.class, true);
                break;
            case R.id.showStudents:
                startAsActivity(ShowStudentsActivity.class);
                break;
        }
    }
}
