package com.example.barna.shop.networkrequest;

import android.content.Context;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.ShowStudentClassesResponseListener;
import com.example.barna.shop.model.ShowStudentGradesResponseListener;
import com.example.barna.shop.model.StudentClass;
import com.example.barna.shop.utils.StoreData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ShowStudentGradesAPI extends BaseAPI {

    Context context;


    public void showStudentGrade(Context context, final int student_id, final ShowStudentGradesResponseListener showStudentGradesResponseListener) {
        RequestBody params = new FormBody.Builder()
                .add(ID_STUDENT, String.valueOf(student_id))
                .build();

        this.context = context;

        newHttpCall(context, SHOW_STUDENT_GRADES_URL, params, new HttpCallback() {

            @Override
            public void onSuccess(JSONObject response) {
                try {
                    showStudentGradesResponseListener.onRespone(response.getString("message"));
                    StoreData.s.saveUserId(response.getInt("id_user"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(JSONArray response) {

                showStudentGradesResponseListener.onShowStudentGrades(manageJson(response.toString()));

            }

            @Override
            public void onError(String error) {
                showStudentGradesResponseListener.onError(error);

            }
        });

    }
    private ArrayList<StudentClass> manageJson(String response) {

        ArrayList<StudentClass> studentClasses= new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String studentCls = jsonObject.getString("class_name");
                int studentGrades = Integer.parseInt(jsonObject.getString("grade"));

                StudentClass studentClass = new StudentClass.Builder().setGrade(studentGrades).setStudentClassName(studentCls).buildStudentClass();

                studentClasses.add(studentClass);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentClasses;
    }
}
