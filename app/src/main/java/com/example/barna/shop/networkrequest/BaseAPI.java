package com.example.barna.shop.networkrequest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.NetworkIsAvailable;
import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.ShowStudentsResponseListener;
import com.example.barna.shop.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.barna.shop.controller.NetworkIsAvailable.isNetworkAvailable;

public abstract class BaseAPI {

    protected final static String BASE_URL = "http://dioclassbook.000webhostapp.com";
    protected final static String API_URL = "/api";
    protected final static String LOGIN_API_URL = "/login_api.php";
    protected final static String REGISTER_API_URL = "/register_api.php";
    protected final static String SHOW_STUDENTS_API_URL = "/show_students_api.php";
    protected final static String SUBMIT_GRADE_API_URL = "/submit_grade_api.php";
    protected final static String SHOW_STUDENT_CLASSES_URL = "/classes_api.php";

    protected final static OkHttpClient CLIENT = new OkHttpClient();

    protected String USER_NAME = "user_name";
    protected String EMAIL = "email";
    protected String PASSWORD = "password";
    protected String CONFIRM_PASSWORD = "confirm_password";
    protected String ID_TEACHER = "id_teacher";
    protected String ID_STUDENT = "id_student";
    protected String STUDENT = "student";
    protected String GRADE = "grade";

    private static RequestBody params;
    private static String url;
    private static HttpCallback callback;

    public BaseAPI() {

    }


    public void newHttpCall(Context context, String url, RequestBody params, HttpCallback callback) {
        BaseAPI.url = url;
        BaseAPI.params = params;
        BaseAPI.callback = callback;

        if (isNetworkAvailable(context)) {
            new MyTask().execute();
        } else {
            callback.onError("Check your Internet Connection");
        }
    }

    public static class MyTask extends AsyncTask<String, Void, String> {

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
            Log.i("BASE_API", result);
            return result;

        }


        @Override
        protected void onPostExecute(String result) {


            try {
                JSONObject jsonResponse = new JSONObject(result);
                JSONObject status = jsonResponse.getJSONObject("status");

                String data = "data";

                if (status.getBoolean("success")) {

                    if (jsonResponse.get(data) instanceof JSONArray) {
                        JSONArray dataArray = jsonResponse.getJSONArray(data);
                        callback.onSuccess(dataArray);

                    } else  {

                        JSONObject dataObject = jsonResponse.getJSONObject(data);
                        callback.onSuccess(dataObject);
                    }

                } else {
                    callback.onError(status.getString("error"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                callback.onError(e.getMessage());
            }
        }

    }

}