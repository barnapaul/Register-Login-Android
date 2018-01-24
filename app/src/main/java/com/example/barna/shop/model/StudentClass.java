package com.example.barna.shop.model;

public class StudentClass {

    private String studentClassName;
    private int grade;


    public StudentClass(Builder builder){
        this.studentClassName = builder.studentClassName;
        this.grade = builder.grade;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public int getGrade() {
        return grade;
    }

    public static class Builder{
        private String studentClassName;
        private int grade;

        public Builder setStudentClassName(String studentClassName) {
            this.studentClassName = studentClassName;
            return this;
        }

        public Builder setGrade(int grade) {
            this.grade = grade;
            return this;
        }

        public StudentClass buildStudentClass() {
            return new StudentClass(this);
        }
    }
}
