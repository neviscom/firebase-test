package com.nevis.firebase.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.nevis.firebase.R;
import com.nevis.firebase.ui.login.SignInActivity;
import com.nevis.firebase.ui.main.MainActivity;

/**
 * @author Nikita Simonov
 */
public class SplashActivity extends AppCompatActivity {

    private static final int DELAY = 1000;

    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startNext();
            }
        }, DELAY);
    }

    private void startNext() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(MainActivity.startIntent(this));
        } else {
            startActivity(SignInActivity.startIntent(this));
        }
        finish();
    }
}
