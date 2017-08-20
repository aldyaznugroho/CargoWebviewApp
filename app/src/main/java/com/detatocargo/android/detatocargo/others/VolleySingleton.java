package com.detatocargo.android.detatocargo.others;

import android.content.Context;

/**
 * Created by Aldyaz on 7/5/2016.
 */
public class VolleySingleton {

    /*private static VolleySingleton volleyInstance;
    private RequestQueue requestQueue;
    private Context context;

    private VolleySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getVolleyInstance(Context context) {
        if (volleyInstance == null) {
            volleyInstance = new VolleySingleton(context);
        }
        return volleyInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }*/
}
