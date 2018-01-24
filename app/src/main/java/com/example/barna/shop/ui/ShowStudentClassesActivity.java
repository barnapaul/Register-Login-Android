package com.example.barna.shop.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.ClassCustomArrayAdapter;
import com.example.barna.shop.controller.ShowsStudentClassesController;
import com.example.barna.shop.model.ShowStudentClassesResponseListener;
import com.example.barna.shop.model.StudentClass;
import com.example.barna.shop.utils.StoreData;

import java.util.ArrayList;

public class ShowStudentClassesActivity extends BaseActivity implements ShowStudentClassesResponseListener {

    ListView listView;
    ClassCustomArrayAdapter adapter;

    Context appContext;

    ShowsStudentClassesController showsStudentClassesController;

    int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_classes);

        appContext = getApplicationContext();

        showsStudentClassesController = new ShowsStudentClassesController(this);

        listView = (ListView) findViewById(R.id.listViewShowClasses);

        int id_student = StoreData.s.getUserId(user_id);

        showsStudentClassesController.showStudentClasses(appContext, id_student, this);


    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onShowStudentClasses(ArrayList<StudentClass> studentClasses) {
        if (adapter == null) {
            adapter = new ClassCustomArrayAdapter(this, studentClasses);
            dismissLoading();
            listView.setAdapter(adapter);
        } else {
            adapter.setStudentUser(studentClasses);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(String message) {
        popUp(message);
    }

    @Override
    public void onError(String error) {
        popUp(error);
    }
}