package com.example.barna.shop.networkrequest;

import android.os.AsyncTask;

import com.example.barna.shop.model.Student;

import okhttp3.OkHttpClient;

public abstract class BaseAPI extends AsyncTask<String, Void, String> {


    protected final static String BASE_URL = "http://dioclassbook.000webhostapp.com";
    protected final static String API_URL = "/api";

    protected final static OkHttpClient CLIENT = new OkHttpClient();

    protected String USER_NAME = "user_name";
    protected String EMAIL = "email" ;
    protected String PASSWORD ="password" ;
    protected String CONFIRM_PASSWORD = "confirm_password";

    protected static Student s;

    public BaseAPI(Student student) {
        this.s = student;
    }

    public BaseAPI() {

    }
}
