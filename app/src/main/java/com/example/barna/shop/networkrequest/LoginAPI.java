package com.example.barna.shop.networkrequest;

import android.content.Context;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.LoginResponseListener;
import com.example.barna.shop.model.UserType;
import com.example.barna.shop.utils.StoreData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class LoginAPI extends BaseAPI {

    Context context;

    public void login(Context context, final String emailStr, final String passwordStr, final LoginResponseListener loginResponse) {
        this.context = context;

        RequestBody params = new FormBody.Builder()
                .add(EMAIL, emailStr)
                .add(PASSWORD, passwordStr)
                .build();

        newHttpCall(context, LOGIN_API_URL, params, new HttpCallback(){

            @Override
            public void onSuccess(JSONObject response) {
                try {
                    int userId = Integer.parseInt(response.getString("id_user"));
                    StoreData.s.saveUserId(userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
            public void onSuccess(JSONArray response) {
                loginResponse.onLoginTeacher();
            }

            @Override
            public void onError(String error) {
                loginResponse.onError(error);
            }
        });
    }
}