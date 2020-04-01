package com.example.staytowin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth authObj;
    private Button loginBtnLoginPage;
    private Button signUpBtnLoginPage;
    private EditText UsernameLogin;
    private EditText PasswordLogin;
    private ProgressDialog whileLogin;
    private TextView errorPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        authObj = FirebaseAuth.getInstance();
        loginBtnLoginPage = (Button)findViewById(R.id.loginButtonLogin);
        signUpBtnLoginPage = (Button)findViewById(R.id.signupButtonLogin);

        errorPanel = (TextView)findViewById(R.id.errorPanel);
        UsernameLogin = (EditText)findViewById(R.id.loginEmail);
        PasswordLogin = (EditText)findViewById(R.id.loginPassword);

        whileLogin = new ProgressDialog(this);

        loginBtnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginValidate(UsernameLogin.getText().toString(), PasswordLogin.getText().toString());
            }
        });

        signUpBtnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(gotoSignUp);
            }
        });
    }



    protected void loginValidate(String xusername, String xpassword) {
        whileLogin.setMessage("Please wait, we are preparing to welcome you!");
        whileLogin.show();
        authObj.signInWithEmailAndPassword(xusername, xpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    whileLogin.dismiss();
                    validateEmail();
                }
                else {
                    whileLogin.dismiss();
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                    errorPanel.setText("Invalid Email/Password! Retry");
                    Toast.makeText(LoginActivity.this, "Check your credentials!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    protected void validateEmail() {
        //FirebaseUser firebaseUser = authObj.getInstance().getCurrentUser();
        //Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(LoginActivity.this, home.class));
        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();

//        if(emailflag){
//            finish();
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        }else{
//            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
    }
}
