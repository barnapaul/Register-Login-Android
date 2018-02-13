package com.example.barna.shop.model;


public class StudentClass {

    private String className;

    private int grade;


    public StudentClass(Builder builder) {
        this.className = builder.studentClassName;
        this.grade = builder.grade;
    }

    public String getClassName() {
        return className;
    }

    public int getGrade() {
        return grade;
    }


    public static class Builder {
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
