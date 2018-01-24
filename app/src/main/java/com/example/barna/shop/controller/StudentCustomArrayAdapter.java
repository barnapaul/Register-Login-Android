package com.example.barna.shop.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;

import com.example.barna.shop.model.Student;
import com.example.barna.shop.R;

import java.lang.reflect.Method;
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
        }

        Student p = usersList.get(position);

        Method[] methods = Student.class.getDeclaredMethods();


        for (Method method : methods) {

            try {

                Method m = p.getClass().getMethod(method.getName(), null);

                if (m.invoke(p) != null && !m.invoke(p).equals(0)) {

                    TextView textView = new TextView(getContext());

                    textView.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

                    textView.setText(String.valueOf(m.invoke(p)));

                    linearLayout.addView(textView);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return convertView;
    }

    public void setUsers(ArrayList<Student> users) {
        this.usersList = users;
    }

}