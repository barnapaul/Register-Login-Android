package com.example.barna.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.ValidEmail;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.R;
import com.example.barna.shop.model.User;
import com.example.barna.shop.networkrequest.RegisterAPI;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    EditText fullName;
    EditText email;
    EditText regPassword;
    EditText confirmPassword;

    Button register;
    Button backToLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = (EditText) findViewById(R.id.fullName);
        email = (EditText) findViewById(R.id.email);
        regPassword = (EditText) findViewById(R.id.regPassword);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        register = (Button) findViewById(R.id.register);
        backToLog = (Button) findViewById(R.id.backToLog);

        register.setOnClickListener(this);
        backToLog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToLog:
                startAsActivity(LoginActivity.class);
                break;
            case R.id.register:
                registerUser();
                break;
        }
    }


    public void registerUser() {
        if (verifyFields()) {

            Student student = new Student.Builder()
                    .setFullName(fullName.getText().toString())
                    .setEmail(email.getText().toString())
                    .setPassword(regPassword.getText().toString())
                    .setConfirmPassword(confirmPassword.getText().toString())
                    .buildPerson();

            setUser(student);

        }

    }

    public boolean verifyFields() {

        if (fieldsEmpty()) {
            popUp("You cannot have empty fields");
        } else if (ValidEmail.validEmail(email)) {
            if (regPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                return true;
            } else {
                popUp("Your phone number should have more or less numbers");
            }
        } else {
            popUp("Retype your email correctly");
        }
        return false;

    }

    public boolean fieldsEmpty() {

        if (fullName.getText().toString().isEmpty()
                || fullName.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()
                || regPassword.getText().toString().isEmpty()
                || confirmPassword.getText().toString().isEmpty()) {

            return true;
        }

        return false;

    }


    public void setUser(Student user) {

        if (User.canRegister(email.getText().toString())) {
//            User.addUser(user);


            new RegisterAPI().execute();

            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

            startAsActivity(LoginActivity.class, true);
        } else {
            popUp("This is email already exists");
        }
    }
}