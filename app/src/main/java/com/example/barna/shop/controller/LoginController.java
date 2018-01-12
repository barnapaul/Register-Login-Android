package com.example.barna.shop.controller;

import com.example.barna.shop.model.LoginResponseListener;
import com.example.barna.shop.networkrequest.BaseAPI;
import com.example.barna.shop.networkrequest.LoginAPI;

public class LoginController extends BaseController{

    private LoginAPI loginAPI;

    public LoginController(BaseActivity baseActivity) {
        super(baseActivity);
        loginAPI = new LoginAPI();
    }



    public void login(String email, String password, LoginResponseListener loginResponseListener) {
        baseActivity.showLoading();
        loginAPI.login(email, password, loginResponseListener);

    }

}
