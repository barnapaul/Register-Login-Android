package com.example.barna.shop.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;

import com.example.barna.shop.model.Person;
import com.example.barna.shop.R;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Person> {

    private ArrayList<Person> usersList;
    private LayoutInflater layoutInflater;


    public CustomArrayAdapter(Context context,  ArrayList<Person> users) {
        super(context, R.layout.row, users);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        usersList = users;

    }


    @Override
    public int getCount() {
        return usersList.size();

    }

    @Override
    public @NonNull View  getView(int position, View convertView,@NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row, parent, false);
        }


        LinearLayout linearLayout = (LinearLayout) convertView;

        Person p = usersList.get(position);

        Method[] fields = Person.class.getDeclaredMethods();


        for (Method method : fields) {


            TextView textView = new TextView(getContext());


            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));


            try {

                Method m = p.getClass().getMethod(method.getName(),null);

                textView.setText(String.valueOf( m.invoke(p)));

            } catch (Exception e) {

                e.printStackTrace();
            }

            linearLayout.addView(textView);

         }

        return convertView;
    }
}


