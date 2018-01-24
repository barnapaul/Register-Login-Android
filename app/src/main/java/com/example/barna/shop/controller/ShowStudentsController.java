package com.example.barna.shop.controller;

import android.content.Context;

import com.example.barna.shop.model.ShowStudentsResponseListener;
import com.example.barna.shop.networkrequest.ShowStudentsAPI;

public class ShowStudentsController extends BaseController {

    Context context;

    private ShowStudentsAPI showStudentsAPI;

    public ShowStudentsController(BaseActivity baseActivity) {
        super(baseActivity);
        showStudentsAPI = new ShowStudentsAPI();
    }



    public void showStudents(Context context, int user_id, ShowStudentsResponseListener showStudentsResponseListener) {
        baseActivity.showLoading();
        this.context =context;
        showStudentsAPI.showStudents(context, user_id, showStudentsResponseListener);
    }
}
