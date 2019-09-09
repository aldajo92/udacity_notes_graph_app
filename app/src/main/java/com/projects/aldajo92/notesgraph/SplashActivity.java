package com.projects.aldajo92.notesgraph;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.projects.aldajo92.notesgraph.login.LoginActivity;
import com.projects.aldajo92.notesgraph.main.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    public static int SPLASH_DURATION = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(this::launchMainActivity, SPLASH_DURATION);
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
    }

    private void launchMainActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
