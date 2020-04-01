package com.example.staytowin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    protected Button menuDrawer;
    protected Button locateMe;
    protected Button logoutBtn1;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        menuDrawer=(Button)findViewById(R.id.menuButton);

        menuDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        locateMe = (Button)findViewById(R.id.locateMe);

        logoutBtn1 = (Button)findViewById(R.id.btnLogout);

        logoutBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(home.this, LoginActivity.class));
                Toast.makeText(getApplicationContext(),"Successfully logged out!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }
}
