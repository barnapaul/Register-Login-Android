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
import com.example.barna.shop.model.Student;
import com.example.barna.shop.model.StudentClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ClassCustomArrayAdapter extends ArrayAdapter<StudentClass> {

    private ArrayList<StudentClass> studentClassList;
    private LayoutInflater layoutInflater;

    ArrayList<String> methodNames=null;
    String methodName = null;
    StudentClass studentClass;

    public ClassCustomArrayAdapter(Context context, ArrayList<StudentClass> studentClasses) {
        super(context, R.layout.row, studentClasses);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        studentClassList = studentClasses;
    }

    @Override
    public int getCount() {

        return studentClassList.size();
    }

    public void sort(int position){

        studentClass = studentClassList.get(position); //ia primul student din lista


//        Method[] methods = StudentClass.class.getDeclaredMethods();

        final ArrayList<Method> methods = new ArrayList<>(Arrays.asList(StudentClass.class.getDeclaredMethods())); // methods contine metode din StudentClass



        for (Method method : methods) {
            methodName = method.getName();

            methodNames = new ArrayList<>();

            try {
                methodNames.add(String.valueOf(studentClass.getClass().getMethod(methodName,null)));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            methodNames.add(String.valueOf(studentClass.getGrade()));



        }
        Collections.sort(methodNames);
    }

    @Override
    public @NonNull
    View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LinearLayout linearLayout;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row, parent, false);
            convertView.setBackgroundResource(R.drawable.shape);

        }

//        StudentClass studentClass = studentClassList.get(position); //ia primul student din lista
        linearLayout = (LinearLayout) convertView; //initializez linearLayout

//        boolean t=true;
//        if (t==true) {
//            t=false;
            sort(position);

//        }
////        Method[] methods = StudentClass.class.getDeclaredMethods();
//
//        final ArrayList<Method> methods = new ArrayList<>(Arrays.asList(StudentClass.class.getDeclaredMethods())); // methods contine metode din StudentClass
//        String methodName = null;
//
//        ArrayList<String> methodNames=null;
//        for (Method method : methods) {
//            methodName = method.getName();
//
//            methodNames = new ArrayList<>();
//
//            try {
//                methodNames.add(String.valueOf(studentClass.getClass().getMethod(methodName,null)));
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            methodNames.add(String.valueOf(studentClass.getGrade()));
//
//            Collections.sort(methodNames);
//
//        }


//
//        Collections.sort(methodNames, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }


//        });

        for (String method : methodNames) { // trece prin fiecare metoda din StudentClass

            try {

//                String methodName = method.getName();// ia-u numele metodei
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



    public void setStudentUser(ArrayList<StudentClass> users) {
        this.studentClassList = users;
    }
}