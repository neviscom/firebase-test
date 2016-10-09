package com.nevis.firebase.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.nevis.firebase.R;
import com.nevis.firebase.ui.main.MainActivity;

/**
 * @author Nikita Simonov
 */
public class SignInActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;

    public static Intent startIntent(@NonNull Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signInByEmail = (Button) findViewById(R.id.btn_email_sign_in);
        Button signInByGoogle = (Button) findViewById(R.id.btn_google_sign_in);

        signInByEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInByEmail();
            }
        });
        signInByGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInByGoogle();
            }
        });
    }

    private void signInByEmail() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().build(),
                RC_SIGN_IN
        );
    }

    private void signInByGoogle() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                AuthUI.EMAIL_PROVIDER,
                                AuthUI.GOOGLE_PROVIDER)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_SIGN_IN:
                if (RESULT_OK == resultCode) {
                    startActivity(MainActivity.startIntent(this));
                    finish();
                }
                break;
        }
    }
}

