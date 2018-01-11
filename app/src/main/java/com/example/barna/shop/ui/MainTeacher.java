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
    ArrayList<Student> listOfStudents;
    ShowStudentsController showStudentsController;

    int user_id;

    final static String TAG= "MainTeacher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);


        showStudentsController = new ShowStudentsController(this);

        listOfStudents = new ArrayList<Student>();

        adapter = new CustomArrayAdapter(this, listOfStudents);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        showStudentsController.showStudents(6,this);

//        Collections.sort(listOfStudents, new Comparator<Student>() {
//            @Override
//            public int compare(Student p1, Student p2) {
//                return p1.getType().compareTo(p2.getType());
//            }
//        });


        if(adapter==null) {
            adapter = new CustomArrayAdapter(this, listOfStudents);
            listView.setAdapter(adapter);
        }else{
            adapter.setUsers(listOfStudents);
            adapter.notifyDataSetChanged();
        }



    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onError(String error) {

    }
}