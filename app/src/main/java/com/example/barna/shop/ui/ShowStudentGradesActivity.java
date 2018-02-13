package com.example.barna.shop.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.ClassCustomArrayAdapter;
import com.example.barna.shop.controller.ShowStudentClassesController;
import com.example.barna.shop.controller.ShowStudentGradesController;
import com.example.barna.shop.model.ShowStudentGradesResponseListener;
import com.example.barna.shop.model.StudentClass;
import com.example.barna.shop.utils.StoreData;

import java.util.ArrayList;

public class ShowStudentGradesActivity extends BaseActivity implements ShowStudentGradesResponseListener {

    ListView listView;
    ClassCustomArrayAdapter adapter;

    Context appContext;

    ShowStudentGradesController showStudentGradesController;

    int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_grades);

        appContext = getApplicationContext();

        showStudentGradesController = new ShowStudentGradesController(this);

        listView = (ListView) findViewById(R.id.listViewShowGrade);

        int id_student = StoreData.s.getUserId(user_id);

        showStudentGradesController.showStudentGrades(appContext, id_student, this);


    }



    @Override
    public void onShowStudentGrades(ArrayList<StudentClass> studentClasses) {

        if (adapter == null) {
            adapter = new ClassCustomArrayAdapter(this, studentClasses);
            dismissLoading();
            listView.setAdapter(adapter);
        } else {
            adapter.setStudentClassList(studentClasses);//update
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(String response) {
        popUp(response);
    }


    @Override
    public void onError(String error) {
        popUp(error);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
