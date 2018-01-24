package com.example.barna.shop.model;

import java.util.ArrayList;

public interface ShowStudentClassesResponseListener {

    void onShowStudentClasses(ArrayList<StudentClass>classes);

    void onResponse(String message);

    void onError(String error);
}
