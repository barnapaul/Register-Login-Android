package com.example.barna.shop.controller;

import com.example.barna.shop.model.RegisterResponseListener;
import com.example.barna.shop.networkrequest.BaseAPI;
import com.example.barna.shop.networkrequest.RegisterAPI;
import com.example.barna.shop.ui.RegisterActivity;

public class RegisterController extends BaseController {

    public RegisterAPI registerAPI;

    public RegisterController(BaseActivity baseActivity) {
        super(baseActivity);
        registerAPI = new RegisterAPI();
    }



    public void register(String full_name, String email, String password, String confirm_password, RegisterResponseListener registerResponseListener) {
        baseActivity.showLoading();
        registerAPI.register(full_name, email, password, confirm_password, registerResponseListener);

    }

}
