package com.example.barna.shop.model;

import com.example.barna.shop.utils.StoreData;

import java.util.ArrayList;

public class User {

    public static void addUser(Student p) {

        ArrayList<Student> users = getUsers();
        users.add(p);
        StoreData.s.updateUsers(users);

    }

    public static ArrayList<Student> getUsers() {
        return StoreData.s.getUsers();
    }

    public static void deleteUser(Student p) {
        ArrayList<Student> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) == p) {
                users.remove(i);
                break;
            }
        }
    }

    public static void deleteUserByPosition(int i) {
        ArrayList<Student> users = getUsers();
        if (users.size() > i) {
            users.remove(i);
        }
    }

    public static boolean userExist(Student p) {
        return getUsers().contains(p);
    }

    public static boolean canLogIn(String email, String pw) {
        ArrayList<Student> users = getUsers();
        for (Student p : users) {
            if (p.getEmail().equals(email)) {
                if (p.getPassword().equals(pw)) {
                    return true;
                }
            }
        }
        return false;


    }

    public static boolean canRegister(String email) {
        ArrayList<Student> users = getUsers();
        for (Student p : users) {
            if (p.getEmail().equals(email)) {
                return false;
            }
        }

        return true;
    }




    public static Student getUserByEmail(String email) {
        ArrayList<Student> users = getUsers();

        for (Student p : users) {
            if (p.getEmail().equals(email))
                return p;
        }

        return null;
    }

}
