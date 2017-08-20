package com.detatocargo.android.detatocargo.objects;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aldyaz on 5/20/2016.
 */
public class UserSessionManager {

    public static UserSessionManager usmInstance;
    public static final String SP_NAME = "userData";
    private SharedPreferences userLocalDb;

    private UserSessionManager(Context context) {
        userLocalDb = context.getSharedPreferences(SP_NAME, 0);
    }

    public static synchronized UserSessionManager getUsmInstance(Context context) {
        if (usmInstance == null) {
            usmInstance = new UserSessionManager(context);
        }
        return usmInstance;
    }

    /**
     * Simpan data user ke SharedPreferences
     */
    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDb.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.putString("phone", user.phone);
        spEditor.putString("address", user.address);
        spEditor.commit();
    }

    /**
     * Buat me-return data user
     */
    public User getUserData() {
        String name = userLocalDb.getString("name", "");
        String email = userLocalDb.getString("email", "");
        String password = userLocalDb.getString("password", "");
        String phone = userLocalDb.getString("phone", "");
        String address = userLocalDb.getString("address", "");

        return User.getUserInstance(name, email, password, phone, address);
    }

    /**
     * Bila login -> true, bila logout -> false
     */
    public boolean getUserLoggedIn() {
        if (userLocalDb.getBoolean("loggedIn", false) == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mengeset login
     * Bila login -> true, bila logout -> false
     */
    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDb.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    /**
     * Hapus data apabila user logout
     */
    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDb.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
