package com.example.barna.shop.networkrequest;

import android.os.AsyncTask;

import com.example.barna.shop.model.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class BaseAPI {


    protected final static String BASE_URL = "http://dioclassbook.000webhostapp.com";
    protected final static String API_URL = "/api";
    protected final static String LOGIN_API_URL = "/login_api.php";
    protected final static String REGISTER_API_URL= "/register_api.php";
    protected final static String SHOW_STUDENTS_API_URL= "/show_students_api.php";

    protected final static OkHttpClient CLIENT = new OkHttpClient();

    protected String USER_NAME = "user_name";
    protected String EMAIL = "email";
    protected String PASSWORD = "password";
    protected String CONFIRM_PASSWORD = "confirm_password";
    protected String ID_TEACHER = "id_teacher";

    private static RequestBody params;
    private static String url;
    private static  HttpCallback callback;

    public BaseAPI() {

    }

    public void newHttpCall( String url, RequestBody params,  HttpCallback callback) {
        BaseAPI.url = url;
        BaseAPI.params = params;
        BaseAPI.callback = callback;
        new MyTask().execute();

    }
    public static class MyTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            try {

                Request request = new Request.Builder()
                        .url(BASE_URL + API_URL + url)
                        .post(params)
                        .build();


                Response response = CLIENT.newCall(request).execute();

                 result = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
                callback.onError(e.getMessage());
            }
            return result;
        }


        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject status = jsonObject.getJSONObject("status");

                if (status.getBoolean("success")) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    callback.onSuccess(data);
                }else{
                    callback.onError(status.getString("error"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                callback.onError(e.getMessage());
            }
        }
    }


}





