package com.detatocargo.android.detatocargo.others;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Aldyaz on 5/19/2016.
 */
public class DetectConnection {

    private static DetectConnection dcInstance;
    private Context context;

    private DetectConnection(Context context) {
        this.context = context;
    }

    public static synchronized DetectConnection getDcInstance(Context context) {
        if (dcInstance == null) {
            dcInstance = new DetectConnection(context);
        }
        return dcInstance;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

}
