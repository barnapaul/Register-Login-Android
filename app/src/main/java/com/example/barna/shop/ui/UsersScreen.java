package com.example.barna.shop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.barna.shop.controller.CustomArrayAdapter;
import com.example.barna.shop.model.Person;
import com.example.barna.shop.model.User;
import com.example.barna.shop.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UsersScreen extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_screen);

        listView = (ListView) findViewById(R.id.listView);


        final ArrayList<Person> users = User.getUsers();

        Collections.sort(users, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getType().compareTo(p2.getType());
            }
        });

        CustomArrayAdapter adapter = new CustomArrayAdapter(this , R.layout.row, users);

        listView.setAdapter(adapter);


    }

}