package com.example.barna.shop.controller;


import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidEmail {

    public  static boolean validEmail(EditText email) {

        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

        String etEmail = email.getText().toString();

        Matcher matcher = Pattern.compile(validemail).matcher(etEmail);
        if (matcher.matches()) {
            return true;
        }
        return false;

    }
}
