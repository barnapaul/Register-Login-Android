package com.example.barna.shop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.barna.shop.controller.CustomArrayAdapter;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.model.User;
import com.example.barna.shop.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UsersScreen extends AppCompatActivity {

    ListView listView;
    CustomArrayAdapter adapter;

    final static String TAG= "UsersScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_screen);

        listView = (ListView) findViewById(R.id.listView);



        final ArrayList<Student> users = User.getUsers();

//        Collections.sort(users, new Comparator<Student>() {
//            @Override
//            public int compare(Student p1, Student p2) {
//                return p1.getType().compareTo(p2.getType());
//            }
//        });


        if(adapter==null) {
            adapter = new CustomArrayAdapter(this, users);
            listView.setAdapter(adapter);
        }else{
            adapter.setUsers(users);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



}