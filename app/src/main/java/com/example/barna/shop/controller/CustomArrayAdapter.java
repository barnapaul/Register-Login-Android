package com.example.barna.shop.controller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.barna.shop.model.Person;
import com.example.barna.shop.R;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Person> {

    ArrayList<Person> usersList;
    LayoutInflater layoutInflater;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        holder.firstName.setText("\nFirst Name: " + p.getFirstName());
        holder.lastName.setText("\n\nLast Name: " + p.getLastName());
        holder.email.setText("\n\n\nEmail: " + p.getEmail());
        holder.phone.setText("\n\n\n\nPhone: " + p.getPhone());
        holder.password.setText("\n\n\n\n\nPassword: " + p.getPassword());
        holder.faculty.setText("");
        holder.year.setText("");
        if (p.getType().equals("Student")) {
            holder.faculty.setText("\n\n\n\n\n\nFaculty: " + p.getFaculty());
            holder.year.setText("\n\n\n\n\n\n\nYear: " + p.getYear());
        }

        return convertView;

    }

    static class ViewHolder {
        public TextView userType;
        public TextView firstName;
        public TextView lastName;
        public TextView email;
        public TextView phone;
        public TextView password;
        public TextView faculty;
        public TextView year;
    }
}
