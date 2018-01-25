package com.example.barna.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.StudentCustomArrayAdapter;
import com.example.barna.shop.controller.ShowStudentsController;
import com.example.barna.shop.model.ShowStudentsResponseListener;
import com.example.barna.shop.R;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.utils.StoreData;

import java.util.ArrayList;

public class MainTeacher extends BaseActivity implements ShowStudentsResponseListener {

    ListView listView;
    StudentCustomArrayAdapter adapter;

    ShowStudentsController showStudentsController;

    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        showStudentsController = new ShowStudentsController(this);

        listView = (ListView) findViewById(R.id.listView);

        int userId = StoreData.s.getUserId(user_id);

        showStudentsController.showStudents(getApplicationContext(), userId, this);


    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onShowStudents(ArrayList<Student> students) {
        if (adapter == null) {
            adapter = new StudentCustomArrayAdapter(this, students);
            dismissLoading();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Student student = (Student) listView.getItemAtPosition(position);
                    Bundle b = new Bundle();
                    b.putString("ID_STUDENT", String.valueOf(student.getIdStudent()));
                    startAsActivity(ListViewActivity.class, b);

                }
            });

            listView.setAdapter(adapter);
        } else {
            adapter.setUsers(students);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onError(String error) {
        popUp(error);
    }

    @Override
    public void onResponse(String response) {
        popUp(response);
    }


}