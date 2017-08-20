package com.detatocargo.android.detatocargo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.detatocargo.android.detatocargo.R;

/**
 * Created by Aldyaz on 5/17/2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent openMainActivity = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(openMainActivity);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splashThread.start();
    }
}
