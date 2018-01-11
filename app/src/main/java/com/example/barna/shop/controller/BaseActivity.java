package com.example.barna.shop.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.barna.shop.networkrequest.LoginAPI;

public class BaseActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    public BaseActivity(){

    }


    public void showLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Check data");
        progressDialog.setMessage("Loading... ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void startAsActivity(Class classToStart, Bundle bundle, boolean closePrevious) {
        Intent intent = new Intent(this, classToStart);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        intent.setFlags(closePrevious ? Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK : Intent.FLAG_ACTIVITY_CLEAR_TOP);
        dismissLoading();
        startActivity(intent);
    }


    public void startAsActivity(Class cls) {
        startAsActivity(cls, null, false);

    }


    public void startAsActivity(Class cls, Bundle b) {
        startAsActivity(cls, b, false);
    }

    public void startAsActivity(Class cls, boolean closePrevious) {

        startAsActivity(cls, null, closePrevious);
    }

    public void popUp(String m) {
        dismissLoading();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        builder.setMessage(m);
        builder.show();
    }


}
