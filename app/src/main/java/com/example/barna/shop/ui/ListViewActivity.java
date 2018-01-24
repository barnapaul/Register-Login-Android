package com.example.barna.shop.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.SubmitGradeController;
import com.example.barna.shop.model.SubmitGradeResponseListener;
import com.example.barna.shop.utils.StoreData;

public class ListViewActivity extends BaseActivity implements SubmitGradeResponseListener{

    Button addButton;
    Button saveButton;
    EditText submitGrade;

    Context appContext;
    int teacherId;
    int teacher_id;
    int studentId;
    int student_id;

    SubmitGradeController submitGradeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        addButton = (Button) findViewById(R.id.addGrade);
        saveButton = (Button) findViewById(R.id.saveGrade);
        submitGrade = (EditText) findViewById(R.id.submitGrade);

        appContext = getApplicationContext();


        submitGradeController = new SubmitGradeController(this);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitGrade.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.INVISIBLE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGrade();
                startAsActivity(MainTeacher.class);
            }
        });

    }

    public void saveGrade(){
        if (!fieldsEmpty()){
            int submitGradeInt = Integer.parseInt(submitGrade.getText().toString());

            teacherId= StoreData.s.getUserId(teacher_id);

            submitGradeController.submitGrade(appContext,studentId,submitGradeInt,teacherId,this);

        }else {
            popUp("You can't have empty fields");
        }
    }

    public boolean fieldsEmpty(){
        return submitGrade.getText().toString().isEmpty();

    }

    @Override
    public void onSuccess(String success) {
        popUp(success);
    }

    @Override
    public void onError(String error) {
        popUp(error);
    }
}
