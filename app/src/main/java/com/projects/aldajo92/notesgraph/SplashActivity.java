package com.projects.aldajo92.notesgraph;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.projects.aldajo92.notesgraph.dashboard.MainActivity;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchMainActivity();
            }
        }, SPLASH_DURATION);
    }

    private void launchMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
