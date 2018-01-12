package com.example.barna.shop.model;

import java.util.ArrayList;

public interface ShowStudentsResponseListener {

    void onShowStudents(ArrayList<Student> students);

    void onError(String error);

}
