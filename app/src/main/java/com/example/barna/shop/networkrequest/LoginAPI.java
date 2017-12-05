package com.example.barna.shop.networkrequest;

import android.os.AsyncTask;
import android.util.Log;

import com.example.barna.shop.utils.LoginResponse;
import com.example.barna.shop.utils.StoreData;
import com.example.barna.shop.utils.UserType;

import java.io.Console;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginAPI extends AsyncTask<String, Void, String> {



    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody postData = new FormBody.Builder()
                    .add(BaseAPI.email, BaseAPI.p.getEmail())
                    .add(BaseAPI.password, BaseAPI.p.getPassword())
                    .build();


            Request request = new Request.Builder()
                    .url(BaseAPI.baseUTL + BaseAPI.urlApi + "/login_api.php")
                    .post(postData)
                    .build();

            Response response = client.newCall(request).execute();
            String result = response.body().string();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

