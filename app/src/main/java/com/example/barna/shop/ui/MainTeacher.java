package com.example.barna.shop.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.CustomArrayAdapter;
import com.example.barna.shop.controller.ShowStudentsController;
import com.example.barna.shop.model.ShowStudentsResponseListener;
import com.example.barna.shop.R;
import com.example.barna.shop.model.Student;

import java.util.ArrayList;

public class MainTeacher extends BaseActivity implements ShowStudentsResponseListener {

    ListView listView;
    CustomArrayAdapter adapter;

    ShowStudentsController showStudentsController;

    int user_id ;

    final static String TAG= "MainTeacher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        showStudentsController = new ShowStudentsController(this);

        listView = (ListView) findViewById(R.id.listView);



        showStudentsController.showStudents(user_id,this);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onShowStudents(ArrayList<Student> students) {
        if(adapter==null) {
            adapter = new CustomArrayAdapter(this, students);
            listView.setAdapter(adapter);
        }else{
            adapter.setUsers(students);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String error) {
        popUp(error);
    }



}