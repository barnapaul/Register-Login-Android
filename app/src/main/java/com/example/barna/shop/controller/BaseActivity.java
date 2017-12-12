package com.example.barna.shop.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.barna.shop.networkrequest.LoginAPI;
import com.example.barna.shop.ui.LoginActivity;

public class BaseActivity extends AppCompatActivity {
    LoginAPI api;

    public LoginAPI api() {
        api = new LoginAPI();
        return api;
    }

    public void showLoading(){
        Log.i("TAG","show Loading");
    }

    public void dismissLoading(){
        Log.i("TAG","dismiss Loading");
    }

    public  void startAsActivity(Class classToStart, Bundle bundle, boolean closePrevious) {
        Intent intent = new Intent(this,classToStart);

        if (bundle!= null) {
            intent.putExtras(bundle);
        }

        intent.setFlags(closePrevious ? Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK:Intent.FLAG_ACTIVITY_CLEAR_TOP);
        dismissLoading();
        startActivity(intent);
    }



    public void startAsActivity(Class cls){
        startAsActivity(cls,null,false);

    }


    public void startAsActivity(Class cls, Bundle b){
        startAsActivity(cls,b,false);
    }

   public void startAsActivity(Class cls, boolean closePrevious) {

        startAsActivity(cls,null,closePrevious);
   }

    public void popUp(String m) {
        dismissLoading();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        builder.setMessage(m);
        builder.show();
    }


}
