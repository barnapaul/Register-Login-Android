package com.example.barna.shop.model;

public enum UserType {
    STUDENT(2),
    PROFESOR(1);

    int type;

    UserType(int type) {
        this.type = type;
    }
}
