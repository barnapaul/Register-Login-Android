package com.example.barna.shop.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.SubmitGradeController;
import com.example.barna.shop.model.SubmitGradeResponseListener;
import com.example.barna.shop.utils.StoreData;

import org.w3c.dom.Text;

public class ListViewActivity extends BaseActivity implements SubmitGradeResponseListener {

    Button saveButton;
    EditText submitGrade;
    TextView gradeIs;

    Context appContext;
    int teacherId;
    int teacher_id;

    SubmitGradeController submitGradeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        saveButton = (Button) findViewById(R.id.saveGrade);
        submitGrade = (EditText) findViewById(R.id.submitGrade);
        gradeIs = (TextView) findViewById(R.id.gradeIs);

        appContext = getApplicationContext();


        submitGradeController = new SubmitGradeController(this);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGrade();

            }
        });

    }

    public void saveGrade() {
        if (!fieldsEmpty()) {
            int submitGradeInt = Integer.parseInt(submitGrade.getText().toString());

            teacherId = StoreData.s.getUserId(teacher_id);

            Bundle b = getIntent().getExtras();
            int id_student = Integer.parseInt(b.getString("ID_STUDENT"));

            submitGradeController.submitGrade(appContext, id_student, submitGradeInt, teacherId, this);

            if (submitGradeInt != 0) {
                gradeIs.setText("Grade is: " + submitGradeInt);
                startAsActivity(MainTeacher.class);
            } else {
                gradeIs.setText("This student don't have a grade yet.");
            }
        } else {
            popUp("You can't have empty fields");


        }

    }

    public boolean fieldsEmpty() {
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
