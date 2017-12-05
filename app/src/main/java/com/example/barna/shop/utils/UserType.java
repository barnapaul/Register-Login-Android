package com.example.barna.shop.utils;

public enum UserType {
    STUDENT(2),
    PROFESOR(1);

    int type;

    UserType(int type) {
        this.type = type;
    }
}
