package com.example.staytowin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth authObj;
    private Button nextButtonSignupPage;
    private Button backButtonSignupPage;
    private EditText personName;
    private EditText personPhone;
    private EditText personEmail;
    private EditText personPassword;
    private EditText personRepeatPassword;
    private ProgressDialog whileLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backButtonSignupPage = (Button)findViewById(R.id.backToLogin);

        backButtonSignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        authObj = FirebaseAuth.getInstance();

        personName = (EditText)findViewById(R.id.personName);
        personEmail = (EditText)findViewById(R.id.personEmail);
        personPhone = (EditText)findViewById(R.id.personPhone);
        personPassword = (EditText)findViewById(R.id.personPassword);
        personRepeatPassword = (EditText)findViewById(R.id.personRepeat);

        nextButtonSignupPage = (Button)findViewById(R.id.gotoNextStep);

        nextButtonSignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personRepeatPassword.equals(personPassword)) {
                    Toast.makeText(getApplicationContext(), "The Passwords do not match!", Toast.LENGTH_SHORT).show();
                } else {

                    /*Intent nextStep = new Intent(SignUpActivity.this, SignUpSecond.class);
                    String xname, xemail, xphone, xpassword;
                    xname = personName.getText().toString();
                    xemail = personEmail.getText().toString();
                    xphone = personPhone.getText().toString();
                    xpassword = personPassword.getText().toString();

                    nextStep.putExtra("personName", xname);
                    nextStep.putExtra("personEmail", xemail);
                    nextStep.putExtra("personPhone", xphone);
                    nextStep.putExtra("personPassword", xpassword);

                    startActivity(nextStep);
                    finish();
                    */
                    String xNewUsername, xNewPassword;

                    xNewUsername = personEmail.getText().toString().trim();
                    xNewPassword = personPassword.getText().toString().trim();

                    authObj.createUserWithEmailAndPassword(xNewUsername, xNewPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                                Intent signupDo = new Intent(new Intent(SignUpActivity.this, LoginActivity.class));
                                Toast.makeText(getApplicationContext(), "SignUp Successful!\nLogin to begin", Toast.LENGTH_SHORT).show();
                                startActivity(signupDo);
                                finish();

                        }
                    });
                }
            }
        });

        backButtonSignupPage = (Button)findViewById(R.id.backToLogin);

        backButtonSignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
