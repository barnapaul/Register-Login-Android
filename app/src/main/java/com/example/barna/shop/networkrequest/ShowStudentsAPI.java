package com.example.barna.shop.networkrequest;

import com.example.barna.shop.model.HttpCallback;
import com.example.barna.shop.model.ShowStudentsResponseListener;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.utils.StoreData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ShowStudentsAPI extends BaseAPI {




    public void showStudents( int user_id, final ShowStudentsResponseListener showStudentsResponseListener) {
        RequestBody params = new FormBody.Builder()
                .add(ID_TEACHER, String.valueOf(user_id))
                .build();


        newHttpCall(SHOW_STUDENTS_API_URL, params, new HttpCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    response.getInt("id_user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                showStudentsResponseListener.onShowStudents(manageJson(response.toString()));

            }

            @Override
            public void onError(String error) {
                showStudentsResponseListener.onError(error);

            }
        });

    }

    private ArrayList<Student> manageJson(String response) {

        ArrayList<Student> students = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String user_name = jsonObject.getString("user_name");
                String email = jsonObject.getString("email");

                Student student = new Student.Builder().setFullName(user_name).setEmail(email).buildStudent();

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }


}
