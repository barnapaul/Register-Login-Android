package com.example.barna.shop.networkrequest;

import android.content.Context;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.ShowStudentClassesResponseListener;
import com.example.barna.shop.model.StudentClass;
import com.example.barna.shop.utils.StoreData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ShowStudentClassesAPI extends BaseAPI {

    Context context;


    public void showStudentClass(Context context, final int user_id, final ShowStudentClassesResponseListener showStudentClassesResponseListener) {
        RequestBody params = new FormBody.Builder()
                .add(ID_STUDENT, String.valueOf(user_id))
                .build();

        this.context = context;

        newHttpCall(context, SHOW_STUDENT_CLASSES_URL, params, new HttpCallback() {

            @Override
            public void onSuccess(JSONObject response) {
                try {
                    showStudentClassesResponseListener.onResponse(response.getString("message"));
                    StoreData.s.saveUserId(response.getInt("id_user"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(JSONArray response) {

                showStudentClassesResponseListener.onShowStudentClasses(manageJson(response.toString()));

            }

            @Override
            public void onError(String error) {
                showStudentClassesResponseListener.onError(error);

            }
        });

    }
    private ArrayList<StudentClass> manageJson(String response) {

        ArrayList<StudentClass> studentClasses= new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String studentCls = jsonObject.getString("class");

                StudentClass studentClass = new StudentClass.Builder().setStudentClassName(studentCls).buildStudentClass();

                studentClasses.add(studentClass);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentClasses;
    }
}
