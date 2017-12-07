package com.example.barna.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.barna.shop.model.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoreData {

    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPref;

    private final static String LOGGEDUSER = "loggedUser";


    //Singleton
    public static StoreData s;
    private final static String ID_TEACHER = "id_teacher";
    private final static String USER = "user";

    public static void init(Context context){

        if (s==null){

             sharedPref = context.getSharedPreferences(ID_TEACHER, Context.MODE_PRIVATE);
             editor = sharedPref.edit();
             editor.apply();
             s = new StoreData();
        }
    }


    public void removeUserId() {
        editor.remove(LOGGEDUSER);
        editor.apply();
    }

    public void saveUserId(int userId) {
        editor.putInt(LOGGEDUSER, userId);
        editor.apply();
    }

    public int getUserId() {
        return sharedPref.getInt(LOGGEDUSER, -1);
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
