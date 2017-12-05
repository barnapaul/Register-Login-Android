package com.example.barna.shop.model;

public class Student {

    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;

    public Student(){

    }

    public Student(Builder builder) {
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.password = builder.password;
        this.confirmPassword = builder.confirmPassword;

    }


    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }


    public static class Builder {

        private String fullName;
        private String email;
        private String password;
        private String confirmPassword;


        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
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

        public Student buildPerson() {
            return new Student(this);
        }

    }
}
