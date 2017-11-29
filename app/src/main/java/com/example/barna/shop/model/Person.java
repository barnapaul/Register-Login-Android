package com.example.barna.shop.model;

import com.example.barna.shop.R;
import com.example.barna.shop.controller.CustomArrayAdapter;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private String type;

    protected String faculty;
    protected int year;

    public Person(){

    }

    public Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.password = builder.password;
        this.confirmPassword = builder.confirmPassword;
        this.type = builder.type;

    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @CustomArrayAdapter.Sort(value = 1)
    public String getType() {
        return type;
    }

    @CustomArrayAdapter.Sort(value = 2)
    public String getFirstName() {
        return firstName;
    }

    @CustomArrayAdapter.Sort(value = 3)
    public String getLastName() {
        return lastName;
    }

    @CustomArrayAdapter.Sort(value = 4)
    public String getEmail() {
        return email;
    }

    @CustomArrayAdapter.Sort(value = 5)
    public String getPhone() {
        return phone;
    }

    @CustomArrayAdapter.Sort(value = 6)
    public String getPassword() {
        return password;
    }


    @CustomArrayAdapter.Sort(value = 7)
    public String getFaculty() {
        return faculty;
    }

    @CustomArrayAdapter.Sort(value = 8)
    public Integer getYear() {
        return year;
    }


    public static class Builder {

        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String password;
        private String confirmPassword;
        private String type;
        public String faculty;
        public int year;


        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public Builder setFaculty(String faculty) {
            this.faculty = faculty;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }


        public Person buildPerson() {
            return new Person(this);
        }

        public Student buildStudent() {
            return new Student(this);
        }


    }
}
