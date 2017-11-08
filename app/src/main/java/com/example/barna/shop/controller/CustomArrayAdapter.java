package com.example.barna.shop.controller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.annotation.NonNull;

import com.example.barna.shop.model.Person;
import com.example.barna.shop.R;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Person> {

    private ArrayList<Person> usersList;
    private LayoutInflater layoutInflater;
    int Resource;


    public CustomArrayAdapter(Context context, int resource, ArrayList<Person> users) {
        super(context, resource, users);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        usersList = users;

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

            holder = new ViewHolder();
            holder.userType = (TextView) convertView.findViewById(R.id.userType);
            holder.firstName = (TextView) convertView.findViewById(R.id.firstName);
            holder.lastName = (TextView) convertView.findViewById(R.id.lastName);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.phone = (TextView) convertView.findViewById(R.id.phone);
            holder.password = (TextView) convertView.findViewById(R.id.password);
            holder.faculty = (TextView) convertView.findViewById(R.id.faculty);
            holder.year = (TextView) convertView.findViewById(R.id.year);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }



        Person p = usersList.get(position);
        holder.userType.setText("User is " + p.getType());
        String firstNameStr = "\n "+ getContext().getString (R.string.first_name) + ": " + p.getFirstName();
        String lastNameStr = "\n\n "+ getContext().getString (R.string.last_name) + ": " + p.getLastName();
        String emailStr = "\n\n\n "+ getContext().getString (R.string.email) + ": " + p.getEmail();
        String phoneStr = "\n\n\n\n " + getContext().getString (R.string.phone) + ": " + p.getPhone();
        String passwordStr = "\n\n\n\n\n " + getContext().getString (R.string.password) + ": " + p.getPassword();
        String facultyStr = "\n\n\n\n\n\n " + getContext().getString (R.string.faculty) + ": " + p.getFaculty();
        String yearStr = "\n\n\n\n\n\n\n\n " + getContext().getString (R.string.year) + ": " + p.getYear();

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
    }
}
