package com.example.barna.shop.controller;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;

import com.example.barna.shop.model.Student;
import com.example.barna.shop.R;

import java.util.ArrayList;

public class StudentCustomArrayAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> usersList;
    private LayoutInflater layoutInflater;


    public StudentCustomArrayAdapter(Context context, ArrayList<Student> users) {
        super(context, R.layout.row, users);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        usersList = users;
    }

    @Override
    public int getCount() {

        return usersList.size();
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LinearLayout linearLayout = null;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row, parent, false);
            linearLayout = (LinearLayout) convertView;

            Student p = usersList.get(position);

            TextView className = new TextView(getContext());
            className.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            className.setGravity(Gravity.CENTER);
            className.setTextSize(30);
            className.setText(p.getFullName());
            linearLayout.addView(className);


            convertView.setBackgroundResource(R.drawable.shape);
        }

        return convertView;
    }

    public void setUsers(ArrayList<Student> users) {
        this.usersList = users;
    }

}