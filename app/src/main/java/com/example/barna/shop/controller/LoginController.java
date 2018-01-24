package com.example.barna.shop.controller;

import android.content.Context;

import com.example.barna.shop.model.LoginResponseListener;
import com.example.barna.shop.networkrequest.BaseAPI;
import com.example.barna.shop.networkrequest.LoginAPI;
import com.example.barna.shop.ui.LoginActivity;

public class LoginController extends BaseController{

    private LoginAPI loginAPI;
    Context context;

    public LoginController(BaseActivity baseActivity) {
        super(baseActivity);
        loginAPI = new LoginAPI();
    }



//    public void login(String email, String password, LoginResponseListener loginResponseListener) {
//        baseActivity.showLoading();
//        loginAPI.login(email, password, loginResponseListener);
//
//    }

    public void login(Context context, String email, String password, LoginResponseListener loginResponseListener) {
        baseActivity.showLoading();
        this.context = context;
        loginAPI.login(context, email, password, loginResponseListener);

    }

}
