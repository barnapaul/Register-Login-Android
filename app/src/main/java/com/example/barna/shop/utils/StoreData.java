package com.example.barna.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.barna.shop.model.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoreData {

    static SharedPreferences.Editor editor;
    static SharedPreferences sharedPref;

    //Singleton
    public static StoreData s;
    private final static String PREFS_NAME = "shop";
    public final static String USER = "user";

    public static void init(Context context){

        if (s==null){

             sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
             editor = sharedPref.edit();
             s = new StoreData();

        }
    }


    public ArrayList<Person> getUsers(){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        ArrayList<Person> persons =  gson.fromJson(sharedPref.getString(USER,null),type);
        return persons==null?new ArrayList<Person>():persons;
    }

    public void updateUsers(ArrayList<Person> persons){
        Gson gson = new Gson();
        String json = gson.toJson(persons);
        editor.putString(USER, json);
        editor.apply();
    }
}
