package com.example.barna.shop.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.barna.shop.R;
import com.example.barna.shop.model.StudentClass;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassCustomArrayAdapter extends ArrayAdapter<StudentClass>{

    private ArrayList<StudentClass> studentClassList;
    private LayoutInflater layoutInflater;


    public ClassCustomArrayAdapter(Context context, ArrayList<StudentClass> studentClasses) {
        super(context, R.layout.row, studentClasses);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        studentClassList = studentClasses;
    }

    @Override
    public int getCount() {

        return studentClassList.size();
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LinearLayout linearLayout = null;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row, parent, false);
            linearLayout = (LinearLayout) convertView;
        }

        StudentClass p = studentClassList.get(position);

        Method[] methods = StudentClass.class.getDeclaredMethods();


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

    public void setStudentUser(ArrayList<StudentClass> users) {
        this.studentClassList = users;
    }

}
