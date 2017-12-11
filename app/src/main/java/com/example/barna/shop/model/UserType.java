package com.example.barna.shop.model;

public enum UserType {
    STUDENT(2),
    TEACHER(1);

    int type;

    UserType(int type) {
        this.type = type;
    }

    public int getType(){
        return this.type;
    }
}
