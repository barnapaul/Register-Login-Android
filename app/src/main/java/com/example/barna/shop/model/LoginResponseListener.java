package com.example.barna.shop.model;

public interface LoginResponseListener {


    void onLoginStudent();

    void onLoginTeacher();

    void onError(String error);


}
