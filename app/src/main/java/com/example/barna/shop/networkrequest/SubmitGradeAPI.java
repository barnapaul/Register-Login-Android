package com.example.barna.shop.networkrequest;

import android.content.Context;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.SubmitGradeResponseListener;
import com.example.barna.shop.utils.StoreData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class SubmitGradeAPI extends BaseAPI{

    Context context;

    public SubmitGradeAPI() {

    }


    public void submitGrade(Context context, final int id_student, final int grade, final int id_teacher,  final SubmitGradeResponseListener submitGradeResponseListener) {
        RequestBody params = new FormBody.Builder()
                .add(STUDENT.concat("[0]"), String.valueOf(id_student))
                .add(GRADE.concat("[0]"), String.valueOf(grade))
                .add(ID_TEACHER, String.valueOf(id_teacher))
                .build();

        this.context = context;

        newHttpCall(context, SUBMIT_GRADE_API_URL, params, new HttpCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    submitGradeResponseListener.onSuccess(response.getString("message"));
                    int userId = Integer.parseInt(response.getString("id_user"));
                    StoreData.s.saveUserId(userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSuccess(JSONArray response) {

            }

            @Override
            public void onError(String error) {
                submitGradeResponseListener.onError(error);
            }
        });

    }
}
