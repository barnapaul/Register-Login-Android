package com.example.barna.shop.networkrequest;

import android.util.Log;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.RegisterResponseListener;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterAPI extends BaseAPI {

    public RegisterAPI() {

    }


    public void register(final String userNameStr, final String emailStr, final String passwordStr, final String confirmPasswordStr, final RegisterResponseListener registerResponseListener) {
        RequestBody params = new FormBody.Builder()
                .add(USER_NAME, userNameStr)
                .add(EMAIL, emailStr)
                .add(PASSWORD, passwordStr)
                .add(CONFIRM_PASSWORD, confirmPasswordStr)
                .build();

        newHttpCall(REGISTER_API_URL, params, new HttpCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.i("TAG","success");
                Log.i("TAG",userNameStr+" "+emailStr +" "+passwordStr+" "+confirmPasswordStr);
                registerResponseListener.onRegister();
            }

            @Override
            public void onError(String error) {

            }
        });

    }
}

