package com.example.staytowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth authObj1;
    Button loginBtn;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateLoginStatus();

        loginBtn = (Button)findViewById(R.id.loginBtn);
        signUpBtn = (Button)findViewById(R.id.signUpBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(gotoLogin);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSignUp = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(gotoSignUp);
            }
        });
    }

    protected void validateLoginStatus() {
        authObj1 = FirebaseAuth.getInstance();
        FirebaseUser currUser = authObj1.getCurrentUser();
        if(currUser!=null) {
            startActivity(new Intent(MainActivity.this, home.class));
            Toast.makeText(getApplicationContext(),"Welcome Back!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
