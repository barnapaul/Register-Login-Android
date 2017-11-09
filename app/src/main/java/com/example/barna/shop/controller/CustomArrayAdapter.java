package com.example.barna.shop.controller;


import android.app.Activity;
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

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Person> {

    private ArrayList<Person> usersList;
    private LayoutInflater layoutInflater;

    Activity activity;


    public CustomArrayAdapter(Context context,  ArrayList<Person> users, Activity activity) {
        super(context, R.layout.row, users);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        usersList = users;
        this.activity = activity;

    }



    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 500;
    }

    @Override
    public @NonNull View  getView(int position, View convertView,@NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row, parent, false);

            holder = new ViewHolder(convertView);


            LinearLayout linearLayout = (LinearLayout) activity.findViewById(R.id.linearLayout);


            TextView textView = new TextView(getContext());

//            textView.setLayoutParams(new LinearLayout.LayoutParams());

            ((LinearLayout) linearLayout).addView(textView);





            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        Person p = usersList.get(position);
        String typeStr = (getContext().getString(R.string.type) + " " + p.getType());
        String firstNameStr =getContext().getString (R.string.first_name) + ": " + p.getFirstName();
        String lastNameStr = getContext().getString (R.string.last_name) + ": " + p.getLastName();
        String emailStr = getContext().getString (R.string.email) + ": " + p.getEmail();
        String phoneStr = getContext().getString (R.string.phone) + ": " + p.getPhone();
        String passwordStr =  getContext().getString (R.string.password) + ": " + p.getPassword();
        String facultyStr =  getContext().getString (R.string.faculty) + ": " + p.getFaculty();
        String yearStr =  getContext().getString (R.string.year) + ": " + p.getYear();

        holder.userType.setText(typeStr);
        holder.firstName.setText(firstNameStr);
        holder.lastName.setText(lastNameStr);
        holder.email.setText(emailStr);
        holder.phone.setText(phoneStr);
        holder.password.setText(passwordStr);
        holder.faculty.setText("");
        holder.year.setText("");
        if (p.getType().equals("Student")) {
            holder.faculty.setText(facultyStr);
            holder.year.setText(yearStr);
        }

        return convertView;

    }

    private static class ViewHolder {
        private TextView userType;
        private TextView firstName;
        private TextView lastName;
        private TextView email;
        private TextView phone;
        private TextView password;
        private TextView faculty;
        private TextView year;


        public ViewHolder(View convertView) {

            userType = (TextView) convertView.findViewById(R.id.userType);
            firstName = (TextView) convertView.findViewById(R.id.firstName);
            lastName = (TextView) convertView.findViewById(R.id.lastName);
            email = (TextView) convertView.findViewById(R.id.email);
            phone = (TextView) convertView.findViewById(R.id.phone);
            password = (TextView) convertView.findViewById(R.id.password);
            faculty = (TextView) convertView.findViewById(R.id.faculty);
            year = (TextView) convertView.findViewById(R.id.year);
        }

    }
}
