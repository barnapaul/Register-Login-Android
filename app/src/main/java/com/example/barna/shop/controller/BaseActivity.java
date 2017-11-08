package com.example.barna.shop.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public  void startAsActivity(Class classToStart, Bundle bundle, boolean closePrevious) {
        Intent intent = new Intent(this,classToStart);

        if (bundle!= null) {
            intent.putExtras(bundle);
        }

        intent.setFlags(closePrevious ? Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK:Intent.FLAG_ACTIVITY_CLEAR_TOP);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        builder.setMessage(m);
        builder.show();
    }
}