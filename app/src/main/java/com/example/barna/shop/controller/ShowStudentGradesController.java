package com.example.barna.shop.controller;

import android.content.Context;

import com.example.barna.shop.model.ShowStudentGradesResponseListener;
import com.example.barna.shop.networkrequest.ShowStudentGradesAPI;

public class ShowStudentGradesController extends BaseController {

    Context context;

    private ShowStudentGradesAPI showStudentGradesAPI;

    public ShowStudentGradesController(BaseActivity baseActivity) {
        super(baseActivity);
        showStudentGradesAPI = new ShowStudentGradesAPI();
    }

    public void showStudentGrades(Context context, int id_student, ShowStudentGradesResponseListener showStudentGradesResponseListener){
        baseActivity.showLoading();
        this.context = context;
        showStudentGradesAPI.showStudentGrade(context,id_student,showStudentGradesResponseListener);
    }
}
