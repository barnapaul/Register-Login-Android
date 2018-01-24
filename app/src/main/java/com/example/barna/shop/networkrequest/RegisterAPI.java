package com.example.barna.shop.networkrequest;

import android.content.Context;
import android.util.Log;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.RegisterResponseListener;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterAPI extends BaseAPI {

    Context context;

    public RegisterAPI() {

    }


    public void register(Context context, final String userNameStr, final String emailStr, final String passwordStr, final String confirmPasswordStr, final RegisterResponseListener registerResponseListener) {
        RequestBody params = new FormBody.Builder()
                .add(USER_NAME, userNameStr)
                .add(EMAIL, emailStr)
                .add(PASSWORD, passwordStr)
                .add(CONFIRM_PASSWORD, confirmPasswordStr)
                .build();

        this.context = context;

        newHttpCall(context, REGISTER_API_URL, params, new HttpCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                Log.i("TAG",response.toString());
                registerResponseListener.onRegister();
            }

            @Override
            public void onSuccess(JSONArray response) {

            }

            @Override
            public void onError(String error) {
                registerResponseListener.onError(error);
            }
        });

    }
}

