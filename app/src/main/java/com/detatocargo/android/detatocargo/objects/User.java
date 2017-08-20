package com.detatocargo.android.detatocargo.objects;

/**
 * Created by Aldyaz on 5/17/2016.
 */
public class User {

    public String name, email, password, phone, address;

    private static User userInstance;

    private User(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    private User(String email, String password) {
        this.email = email;
        this.password = password;
        this.name = "";
    }

    public static synchronized User getUserInstance(String name, String email, String password, String phone, String address) {
        if (userInstance == null) {
            userInstance = new User(name, email, password, phone, address);
        }
        return userInstance;
    }

    public static synchronized User getUserInstance(String email, String password) {
        if (userInstance == null) {
            userInstance = new User(email, password);
        }
        return userInstance;
    }
}
