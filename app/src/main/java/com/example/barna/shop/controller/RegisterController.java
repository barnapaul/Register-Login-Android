package com.example.barna.shop.controller;

import android.content.Context;

import com.example.barna.shop.model.RegisterResponseListener;
import com.example.barna.shop.networkrequest.BaseAPI;
import com.example.barna.shop.networkrequest.RegisterAPI;
import com.example.barna.shop.ui.RegisterActivity;

public class RegisterController extends BaseController {

    public RegisterAPI registerAPI;
    Context context;

    public RegisterController(BaseActivity baseActivity) {
        super(baseActivity);
        registerAPI = new RegisterAPI();
    }



    public void register(Context context, String full_name, String email, String password, String confirm_password, RegisterResponseListener registerResponseListener) {
        baseActivity.showLoading();
        this.context =context;
        registerAPI.register(context, full_name, email, password, confirm_password, registerResponseListener);

    }

}
