package com.example.barna.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.model.Person;
import com.example.barna.shop.R;

import com.example.barna.shop.model.User;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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

        showAllUsers.setOnClickListener(this);
        logout.setOnClickListener(this);


        showPersonInfo();

     }


    public void showPersonInfo() {

        String email = getIntent().getStringExtra("USER_EMAIL");

        Person user = User.getUserByEmail(email);

//        Context ctx = this;
//        ctx.getResources().getString(R.string.first_name);


        if (user != null) {


            String firstNameStr = getString(R.string.first_name) + ": " + user.getFirstName();
            String lastNameStr = getString(R.string.last_name) + ": " + user.getLastName();
            String emailStr = getString(R.string.email) + ": " + user.getEmail();
            String phoneStr = getString(R.string.phone) + ": " + user.getPhone();
            String passwordStr = getString(R.string.password) + ": " + user.getPassword();
            String facultyStr = getString(R.string.faculty) + ": " + user.getFaculty();
            String yearStr = getString(R.string.year) + ": " + user.getYear();


            firstNameDetail.setText(firstNameStr);
            lastNameDetail.setText(lastNameStr);
            emailDetail.setText(emailStr);
            phoneDetail.setText(phoneStr);
            passwordDetail.setText(passwordStr);

            if (user.getType().equals("Student")) {
                facultyDetail.setText(facultyStr);
                yearDetail.setText(yearStr);

            }
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.showUsers:
                startAsActivity(UsersScreen.class);
                break;
            case R.id.logout:
                startAsActivity(LoginActivity.class,true);
                break;
        }

    }
}
