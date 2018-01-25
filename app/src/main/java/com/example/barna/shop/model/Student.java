package com.example.barna.shop.model;

public class Student {

    private int idStudent;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;


    public Student(Builder builder) {
        this.idStudent = builder.idStudent;
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

    public int getIdStudent() {
        return idStudent;
    }



    public static class Builder {


        private int idStudent;
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

        public Builder setIdStudent(int idStudent){
            this.idStudent = idStudent;
            return this;
        }

        public Student buildStudent() {
            return new Student(this);
        }

    }
}
