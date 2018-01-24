package com.example.barna.shop.controller;


import android.content.Context;

import com.example.barna.shop.model.ShowStudentClassesResponseListener;
import com.example.barna.shop.networkrequest.ShowStudentClassesAPI;

public class ShowsStudentClassesController extends BaseController {

    Context context;

    private ShowStudentClassesAPI showStudentClassesAPI;

    public ShowsStudentClassesController(BaseActivity baseActivity) {
        super(baseActivity);
        showStudentClassesAPI = new ShowStudentClassesAPI();
    }

    public void showStudentClasses(Context context, int id_student, ShowStudentClassesResponseListener showStudentClassesResponseListener){
        baseActivity.showLoading();
        this.context = context;
        showStudentClassesAPI.showStudentClass(context,id_student,showStudentClassesResponseListener);
    }


}
