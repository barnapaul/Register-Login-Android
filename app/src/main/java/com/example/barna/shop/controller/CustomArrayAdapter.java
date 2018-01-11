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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CustomArrayAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> usersList;
    private LayoutInflater layoutInflater;


    public CustomArrayAdapter(Context context, ArrayList<Student> users) {
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

        Arrays.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method s1, Method s2) {
                Sort sort1 = s1.getAnnotation(Sort.class);
                Sort sort2 = s2.getAnnotation(Sort.class);
                if (sort1 != null && sort2 != null) {
                    return sort1.value() - sort2.value();
                }
                return s1.getName().compareTo(s2.getName());
            }
        });

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

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Sort {

        int value();
    }
}