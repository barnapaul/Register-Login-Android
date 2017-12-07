package com.example.barna.shop.networkrequest;

import android.util.Log;

import com.example.barna.shop.model.LoginResponse;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.model.UserType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginAPI extends BaseAPI {

    public LoginAPI(Student student) {
        super(student);
    }

    public LoginAPI() {
        super();
    }

    Boolean loginStatus = false;

    @Override
    protected String doInBackground(String... strings) {
        try {


            RequestBody postData = new FormBody.Builder()
                    .add(EMAIL, s.getEmail())
                    .add(PASSWORD, s.getPassword())
                    .build();


            Request request = new Request.Builder()
                    .url(BASE_URL + API_URL + "/login_api.php")
                    .post(postData)
                    .build();

            Response response = CLIENT.newCall(request).execute();

            String result = response.body().string();

            try {
                JSONObject jsonObject = new JSONObject(result);

                JSONObject status = jsonObject.getJSONObject("status");
                loginStatus = status.getBoolean("success");
//
//                JSONArray array = jsonObject.getJSONArray("data");
//
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject obj = array.getJSONObject(i);
//
//                    int userId = obj.getInt("id_user");
//                    String userName = obj.getString("user_name");
//                    String email = obj.getString("email");
//                }

                Log.i("TAG", result);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void login(String emailEt, String passwordEt, LoginResponse loginResponse) {
        s = new Student.Builder().setEmail(emailEt).setPassword(passwordEt).buildPerson();

        execute();




        if (loginStatus)
            loginResponse.onLogin();
        else
            loginResponse.onError();
    }




}

