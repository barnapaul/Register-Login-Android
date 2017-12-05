package com.example.barna.shop.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.ValidEmail;
import com.example.barna.shop.model.User;
import com.example.barna.shop.R;
import com.example.barna.shop.networkrequest.LoginAPI;
import com.example.barna.shop.networkrequest.RegisterAPI;
import com.example.barna.shop.utils.LoginResponse;
import com.example.barna.shop.utils.StoreData;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    Button login;
    Button register;

    SharedPreferences sharedPref;

    public final static String PREFS_NAME = "userInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(this);
        login.setOnClickListener(this);

        sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

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
//            if (User.canLogIn(email.getText().toString(), password.getText().toString())) {

//                Bundle bundle = new Bundle();
//                bundle.putString("USER_EMAIL", email.getText().toString());

            String emailEt = email.getEditableText().toString();
            String passwordEt = password.getEditableText().toString();


//            api().login(emailEt, passwordEt, new LoginResponse() {
//                onLogin() {
//                    startAsActivity(MainStudent.class);
//                }
//
//                onError() {
//                    popUp("Your email or your password are invalid");
//                }
//            });
//            new LoginAPI(emailEt, passwordEt).execute();

//           int userId = StoreData.s.getUserId();
//            StoreData.s.saveUserId(7);
//            userId = StoreData.s.getUserId();

            startAsActivity(MainStudent.class);
        } else {
            popUp("Retype your email correctly");
        }
    }

}