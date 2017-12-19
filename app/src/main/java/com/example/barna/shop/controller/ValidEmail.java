package com.example.barna.shop.controller;


import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidEmail {

    public  static boolean validEmail(EditText email) {

        final String EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

        String etEmail = email.getText().toString();

        Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(etEmail);
        if (matcher.matches()) {
            return true;
        }
        return false;

    }

    public static boolean validPassword(EditText password){

        final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$";

        String etPassword = password.getText().toString();

        Matcher matcher = Pattern.compile(PASSWORD_PATTERN).matcher(etPassword);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
