package com.example.barna.shop.model;


import java.util.ArrayList;

public interface ShowStudentGradesResponseListener {

    void onShowStudentGrades(ArrayList<StudentClass> classes);

    void onResponse(String response);

    void onError(String error);
}
