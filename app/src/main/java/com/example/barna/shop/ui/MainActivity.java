package com.example.barna.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.barna.shop.model.Person;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.R;

import com.example.barna.shop.model.User;

public class MainActivity extends AppCompatActivity {

    TextView userTypeDetail;
    TextView firstNameDetail;
    TextView lastNameDetail;
    TextView emailDetail;
    TextView phoneDetail;
    TextView facultyDetail;
    TextView yearDetail;
    TextView passwordDetail;
    Button logout;
    Button showAllUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userTypeDetail = (TextView) findViewById(R.id.userType);
        firstNameDetail = (TextView) findViewById(R.id.firstNameDetail);
        lastNameDetail = (TextView) findViewById(R.id.lastNameDetail);
        emailDetail = (TextView) findViewById(R.id.emailDetail);
        phoneDetail = (TextView) findViewById(R.id.phoneDetail);
        passwordDetail = (TextView) findViewById(R.id.passwordDetail);
        facultyDetail = (TextView) findViewById(R.id.facultyDetail);
        yearDetail = (TextView) findViewById(R.id.yearDetail);
        showAllUsers = (Button) findViewById(R.id.showUsers);

        logout = (Button) findViewById(R.id.logout);


        showPersonInfo();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logout);


            }
        });

        showAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showUsers = new Intent(MainActivity.this, UsersScreen.class);
                startActivity(showUsers);

            }
        });


    }


    public void showPersonInfo() {

        String email = getIntent().getStringExtra("USER_EMAIL");

        Person user = User.getUserByEmail(email);

        userTypeDetail.setText("You are " + user.getType());
        firstNameDetail.setText("First Name: " + user.getFirstName());
        lastNameDetail.setText("Last Name: " + user.getLastName());
        emailDetail.setText("Email: " + user.getEmail());
        phoneDetail.setText("Phone: " + user.getPhone());
        passwordDetail.setText("Password: " + user.getPassword());

        if (user.getType().equals("Student")) {

            facultyDetail.setText("Faculty: " + user.getFaculty());
            yearDetail.setText("Year: " + user.getYear());


        }
    }


}
