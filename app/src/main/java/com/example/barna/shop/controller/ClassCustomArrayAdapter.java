package com.example.barna.shop.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
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
import java.util.Arrays;
import java.util.Collections;

public class ClassCustomArrayAdapter extends ArrayAdapter<StudentClass> {

    private ArrayList<StudentClass> studentClassList;
    private LayoutInflater layoutInflater;


    public ClassCustomArrayAdapter(Context context, ArrayList<StudentClass> studentClasses) {
        super(context, R.layout.row, studentClasses);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        studentClassList = studentClasses;
    }

    public void setStudentClassList(ArrayList<StudentClass> studentClassList) {
        this.studentClassList = studentClassList;
    }

    @Override

    public int getCount() {

        return studentClassList.size();
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row, parent, false);
            convertView.setBackgroundResource(R.drawable.shape);
            return initializeAndPopulateView(convertView, position);

        } else {
            return convertView;
        }
    }


    private ArrayList<String> sort(ArrayList<String> methodNames) {

        Collections.sort(methodNames);
        return methodNames;
    }

    private View initializeAndPopulateView(View convertView, int position) {

        LinearLayout linearLayout = (LinearLayout) convertView; //initializez linearLayout

        StudentClass studentClass = studentClassList.get(position); //ia elementul de la positia respectiva

        for (String methodName : getSortedMethodNames(studentClass)) {

            try {

                Method m = studentClass.getClass().getMethod(methodName, null);//i-au metoda studentului de pe pozitia position


                if (m.invoke(studentClass) != null && !m.invoke(studentClass).equals(0)) {

                    TextView textView = new TextView(getContext());

                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f));
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextSize(25);

                    textView.setText(String.valueOf(m.invoke(studentClass)));

                    linearLayout.addView(textView);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return convertView;
    }

    private ArrayList<String> getSortedMethodNames(StudentClass studentClass) {
        ArrayList<Method> methods = new ArrayList<>(Arrays.asList(StudentClass.class.getDeclaredMethods()));// methods contine metode din StudentClass

        ArrayList<String> methodNames = new ArrayList<>();

        for (Method method : methods) { // trece prin fiecare metoda din StudentClass

            String methodName = method.getName();// ia-u numele metodei
            try {

                Method m = studentClass.getClass().getMethod(methodName, null);//i-au metoda studentului de pe pozitia position
                if (m.invoke(studentClass) != null && !m.invoke(studentClass).equals(0)) {

                    methodNames.add(methodName);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        methodNames = sort(methodNames);
        return methodNames;

    }





}