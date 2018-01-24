package com.example.barna.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.RegisterController;
import com.example.barna.shop.controller.ValidEmail;
import com.example.barna.shop.model.RegisterResponseListener;
import com.example.barna.shop.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterResponseListener {

    EditText fullName;
    EditText email;
    EditText regPassword;
    EditText confirmPassword;

    RegisterController registerController;

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

        registerController = new RegisterController(this);

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

            String fullNameStr = fullName.getEditableText().toString();
            String emailStr = email.getEditableText().toString();
            String passwordStr = regPassword.getEditableText().toString();
            String confirmPassword = this.confirmPassword.getEditableText().toString();


            registerController.register(getApplicationContext(),fullNameStr, emailStr, passwordStr, confirmPassword, this);

        }
    }

    public boolean verifyFields() {

        if (fieldsEmpty()) {
            popUp("You cannot have empty fields");
        } else if (ValidEmail.validEmail(email)) {
            if (ValidEmail.validPassword(regPassword)) {
                if (regPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    return true;
                } else {
                    popUp("Retype your password correctly");
                }
            } else {
                popUp("You should have at least one upper case, one lower case letter, one digit and 8 characters");
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

    @Override
    public void onRegister() {
        startAsActivity(LoginActivity.class, true);
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String error) {
        popUp(error);
    }
}