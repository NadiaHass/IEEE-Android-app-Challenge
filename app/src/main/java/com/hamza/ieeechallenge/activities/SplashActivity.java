package com.hamza.ieeechallenge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hamza.ieeechallenge.R;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setFlag();
        firebaseAuth = FirebaseAuth.getInstance();
        new Handler().postDelayed(this::checkAuthState, 3500);
    }

    private void setFlag() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void checkAuthState() {
        FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user == null){
                startActivity(new Intent(SplashActivity.this , SendOtpActivity.class));
            }else{
                startActivity(new Intent(SplashActivity.this , MainActivity.class));
            }
            finish();
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}