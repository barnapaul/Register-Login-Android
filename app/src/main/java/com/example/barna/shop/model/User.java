package com.example.barna.shop.model;

import com.example.barna.shop.utils.StoreData;

import java.util.ArrayList;

public class User {

    public static void addUser(Person p) {

        ArrayList<Person> users = getUsers();
        users.add(p);
        StoreData.s.updateUsers(users);

    }

    public static ArrayList<Person> getUsers() {
        return StoreData.s.getUsers();
    }

    public static void deleteUser(Person p) {
        ArrayList<Person> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) == p) {
                users.remove(i);
                break;
            }
        }
    }

    public static void deleteUserByPosition(int i) {
        ArrayList<Person> users = getUsers();
        if (users.size() > i) {
            users.remove(i);
        }
    }

    public static boolean userExist(Person p) {
        return getUsers().contains(p);
    }

    public static boolean canLogIn(String email, String pw) {
        ArrayList<Person> users = getUsers();
        for (Person p : users) {
            if (p.getEmail().equals(email)) {
                if (p.getPassword().equals(pw)) {
                    return true;
                }
            }
        }
        return false;


    }

    public static boolean canRegister(String email) {
        ArrayList<Person> users = getUsers();
        for (Person p : users) {
            if (p.getEmail().equals(email)) {
                return false;
            }
        }

        return true;
    }




    public static Person getUserByEmail(String email) {
        ArrayList<Person> users = getUsers();

        for (Person p : users) {
            if (p.getEmail().equals(email))
                return p;
        }

        return null;
    }




}
