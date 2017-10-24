package com.example.barna.shop.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barna.shop.model.User;
import com.example.barna.shop.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    public final static String TAG = "LoginActivity";

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

        sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check the empty fields
                if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
                    popUp("You cannot have emthy fields");
                } else if (validEmail()) {
                    if (User.canLogIn(email.getText().toString(), password.getText().toString())) {
                        Intent login = new Intent(LoginActivity.this, MainActivity.class);
                        login.putExtra("USER_EMAIL", email.getText().toString());
                        startActivity(login);

                    } else {
                        popUp("Your email or your password is wrong");
                    }


                } else {
                    popUp("Your email or your password are invalid");
                }
            }

        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });


    }

    public boolean validEmail() {
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

        String etEmail = email.getText().toString();

        Matcher matcher = Pattern.compile(validemail).matcher(etEmail);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public void popUp(String m) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setPositiveButton("OK", null);
        builder.setMessage(m);
        builder.show();
    }


    public void log(String msg) {
        Log.d(TAG, msg);
    }


}