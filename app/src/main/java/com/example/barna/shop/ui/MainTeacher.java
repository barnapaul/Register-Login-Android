package com.example.barna.shop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.barna.shop.R;

public class MainTeacher extends AppCompatActivity {

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

    }
}
