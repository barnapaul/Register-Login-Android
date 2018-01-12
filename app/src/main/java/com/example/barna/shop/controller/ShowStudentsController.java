package com.example.barna.shop.controller;

import com.example.barna.shop.model.ShowStudentsResponseListener;
import com.example.barna.shop.networkrequest.ShowStudentsAPI;

public class ShowStudentsController extends BaseController {


    private ShowStudentsAPI showStudentsAPI;

    public ShowStudentsController(BaseActivity baseActivity) {
        super(baseActivity);
        showStudentsAPI = new ShowStudentsAPI();
    }



    public void showStudents(int user_id, ShowStudentsResponseListener showStudentsResponseListener) {
        baseActivity.showLoading();
        showStudentsAPI.showStudents(user_id, showStudentsResponseListener);

    }
}
