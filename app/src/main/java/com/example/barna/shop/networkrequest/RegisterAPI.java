package com.example.barna.shop.networkrequest;


import android.os.AsyncTask;
import android.util.Log;

import com.example.barna.shop.model.Student;
import com.example.barna.shop.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterAPI extends BaseAPI {


//    class AsyncTaskRunner extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//
//                RequestBody postData = new FormBody.Builder()
//                        .add(USER_NAME, s.getFullName())
//                        .add(EMAIL, s.getEmail())
//                        .add(PASSWORD, s.getPassword())
//                        .add(CONFIRM_PASSWORD, s.getConfirmPassword())
//                        .build();
//
//
//                Request request = new Request.Builder()
//                        .url(BASE_URL + API_URL + REGISTER_API_URL)
//                        .post(postData)
//                        .build();
//
//                Response response = CLIENT.newCall(request).execute();
//
//                return response.body().string();
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//    }
}
