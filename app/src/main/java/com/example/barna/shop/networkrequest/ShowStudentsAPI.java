package com.example.barna.shop.networkrequest;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.ShowStudentsResponseListener;
import com.example.barna.shop.model.Student;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ShowStudentsAPI extends BaseAPI {


    public static List<Student> students;

    public void showStudents(final int user_id, final ShowStudentsResponseListener showStudentsResponseListener) {
        RequestBody params = new FormBody.Builder()
                .add(ID_TEACHER, String.valueOf(user_id))
                .build();

        newHttpCall(SHOW_STUDENTS_API_URL, params, new HttpCallback() {
            @Override
            public void onSuccess(JSONObject response) {

                manageJson(response.toString());

            }

            @Override
            public void onError(String error) {
                showStudentsResponseListener.onError(error);

            }
        });

    }

    private void manageJson(String response) {
        try {
            students = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String user_name = jsonObject.getString("user_name");
                String email = jsonObject.getString("email");
                String password = jsonObject.getString("password");
                String confirm_password = jsonObject.getString("confirm_password");

                Student student = new Student.Builder().setFullName(user_name).setEmail(email).setPassword(password).setConfirmPassword(confirm_password).buildStudent();

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
