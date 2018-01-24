package com.example.barna.shop.controller;

import android.content.Context;
import android.view.View;

import com.example.barna.shop.model.SubmitGradeResponseListener;
import com.example.barna.shop.networkrequest.SubmitGradeAPI;

public class SubmitGradeController extends BaseController{

    Context context;

    private SubmitGradeAPI submitGradeAPI;

    public SubmitGradeController(BaseActivity baseActivity) {
        super(baseActivity);
        submitGradeAPI = new SubmitGradeAPI();
    }

    public void submitGrade(Context context, int id_student, int grade, int id_teacher, SubmitGradeResponseListener submitGradeResponseListener){
        baseActivity.showLoading();
        this.context= context;
        submitGradeAPI.submitGrade(context,id_student,grade,id_teacher,submitGradeResponseListener);
    }


}
