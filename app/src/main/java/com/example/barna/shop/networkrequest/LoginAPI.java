package com.example.barna.shop.networkrequest;

import android.util.Log;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.LoginResponseListener;
import com.example.barna.shop.model.UserType;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class LoginAPI extends BaseAPI {

    public void login(final String emailStr, final String passwordStr, final LoginResponseListener loginResponse) {
        RequestBody params = new FormBody.Builder()
                .add(EMAIL, emailStr)
                .add(PASSWORD, passwordStr)
                .build();


        newHttpCall(LOGIN_API_URL, params, new HttpCallback() {
            @Override
            public void onSuccess(JSONObject response) {

                try {
                    if (response.getInt("user_type") == UserType.TEACHER.getType()) {
                        loginResponse.onLoginTeacher();
                    } else {
                        loginResponse.onLoginStudent();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    loginResponse.onError(e.getMessage());
                }
            }

            @Override
            public void onError(String error) {
                loginResponse.onError(error);
            }
        });

    }

}


