package com.example.barna.shop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.utils.StoreData;

public class MainTeacher extends BaseActivity implements View.OnClickListener {

    Button showStudents;
    Button submitGrade;
    Button editGrade;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        showStudents = (Button) findViewById(R.id.showStudents);
        submitGrade = (Button) findViewById(R.id.submitGrade);
        editGrade = (Button) findViewById(R.id.editGrade);
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(this);
        showStudents.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.logout:
                startAsActivity(LoginActivity.class, true);
                break;
            case R.id.showStudents:
                startAsActivity(UsersScreen.class);
        }

    }
}
