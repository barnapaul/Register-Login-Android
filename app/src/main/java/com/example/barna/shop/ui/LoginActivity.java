package com.example.barna.shop.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.LoginController;
import com.example.barna.shop.controller.ValidEmail;
import com.example.barna.shop.R;
import com.example.barna.shop.model.LoginResponseListener;
import com.example.barna.shop.utils.StoreData;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginResponseListener {


    EditText email;
    EditText password;
    Button loginButton;
    Button register;
    CheckBox checkBox;

    Context appContext;

    LoginController loginController;

    SharedPreferences sharedPref;

    public final static String PREFS_NAME = "userInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        checkBox = (CheckBox) findViewById(R.id.ch_rememberMe);

        register.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        loginController = new LoginController(this);

        sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        appContext = getApplication();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register:
                startAsActivity(RegisterActivity.class);
                break;
            case R.id.login:
                verifyLogin();
                break;

        }

    }

    public void verifyLogin() {
        if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
            popUp("You cannot have empty fields");
        } else if (ValidEmail.validEmail(email)) {


            String emailStr = email.getEditableText().toString();
            String passwordStr = password.getEditableText().toString();


            loginController.login(appContext, emailStr, passwordStr, this);

        } else {
            popUp("Retype your email correctly");
        }
    }


    @Override
    public void onLoginStudent() {
        if (checkBox.isChecked()) {
            StoreData.s.isLoggedIn(true);
            startAsActivity(MainStudent.class);
        } else {
            startAsActivity(MainStudent.class, true);
        }
    }

    @Override
    public void onLoginTeacher() {
        if (checkBox.isChecked()) {
            StoreData.s.isLoggedInTeacher(true);
        }

        startAsActivity(MainTeacher.class);
    }

    @Override
    public void onError(String error) {
        popUp(error);
    }


}