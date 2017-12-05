package com.example.barna.shop.networkrequest;

import com.example.barna.shop.model.Student;

public class BaseAPI {

    protected final static String baseUTL = "http://dioclassbook.000webhostapp.com";
    protected final static String urlApi = "/api";

    protected static String user_name;
    protected static String email;
    protected static String password;
    protected static String confirm_password;

    protected static Student p;

    public BaseAPI(Student person) {
        this.p = person;
    }

}
