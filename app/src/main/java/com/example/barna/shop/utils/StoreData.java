package com.example.barna.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.barna.shop.model.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoreData {

    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPref;


    //Singleton
    public static StoreData s;
    private final static String ID_USER = "id_user";
    private final static String USER = "user";

    public static void init(Context context){

        if (s==null){

             sharedPref = context.getSharedPreferences(ID_USER , Context.MODE_PRIVATE);
             editor = sharedPref.edit();
             editor.apply();
             s = new StoreData();
        }
    }

    public void saveUserId(int userId){
        editor.putInt(ID_USER,userId);
        editor.commit();
    }

    public int getUserId(int userId){
        int idUser=  sharedPref.getInt(ID_USER,userId);
        Log.i("sharepref teacherid", String.valueOf(idUser));
        return idUser;
    }


    public ArrayList<Student> getUsers(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Student>>(){}.getType();
        ArrayList<Student> persons =  gson.fromJson(sharedPref.getString(USER,null),type);
        return persons==null?new ArrayList<Student>():persons;
    }

    public void updateUsers(ArrayList<Student> persons){
        Gson gson = new Gson();
        String json = gson.toJson(persons);
        editor.putString(USER, json);
        editor.apply();
    }
}
