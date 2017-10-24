package com.example.barna.shop.model;

public class Student extends Person {



    public Student(Builder builder) {
        super(builder);
        this.faculty = builder.faculty;
        this.year = builder.year;
    }


    public String getFaculty() {
        return faculty;
    }

    public int getYear() {
        return year;
    }


}
