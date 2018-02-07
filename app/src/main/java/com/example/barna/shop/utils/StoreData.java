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


    //Singleton
    public static StoreData s;
    private final static String SCHOOL = "school";
    private final static String ID_USER = "id_user";
    private final static String USER = "user";
    private final static String USER_EMAIL = "user_email";
    private final static String IS_LOGGED_IN = "is_logged_in";
    private final static String IS_LOGGED_IN_TEACHER = "is_logged_in_teacher";


    public static void init(Context context){

        if (s==null){

             sharedPref = context.getSharedPreferences(SCHOOL , Context.MODE_PRIVATE);
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
        return idUser;
    }

    public void isLoggedIn(Boolean loggedIn){
        editor.putBoolean(IS_LOGGED_IN,loggedIn);
        editor.commit();

    }

    public void isLoggedInTeacher(Boolean loggedIn){
        editor.putBoolean(IS_LOGGED_IN_TEACHER,loggedIn);
        editor.commit();

    }

    public boolean getIsLoggedIn(){
        return sharedPref.getBoolean(IS_LOGGED_IN,false);
    }

    public boolean getIsLoggedInTeacher(){
        return sharedPref.getBoolean(IS_LOGGED_IN_TEACHER,false);
    }



    public void deleteLoginUser(){
        editor.remove(IS_LOGGED_IN);
        editor.clear();
        editor.commit();
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
