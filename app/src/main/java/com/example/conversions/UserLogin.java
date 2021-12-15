package com.example.conversions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.AuthCredential;

public class UserLogin extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "UserLogin";
    private TextView Register, ForgotPassword;
    private EditText textEmail, textPassword;
    private Button Login, Guest;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        Register = (TextView) findViewById(R.id.register);
        Register.setOnClickListener(this);

        Login = (Button) findViewById(R.id.signin);
        Login.setOnClickListener(this);

        Guest = (Button) findViewById(R.id.button13);
        Guest.setOnClickListener(this);

        textEmail = (EditText) findViewById(R.id.email);
        textPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        ForgotPassword = (TextView) findViewById(R.id.forgotPassword);
        ForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.signin:
                userLogin();
                break;

            case R.id.button13:
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;


        }
    }

    private void userLogin() {
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        if(email.isEmpty()){
            textEmail.setError("Email is required");
            textEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textEmail.setError("Enter Valid Email please!");
            textEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            textPassword.setError("Password is required");
            textPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            textPassword.setError("Minimum length of password should be 6 characters!");
            textPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(UserLogin.this, MainActivity.class));
                    }
                    else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(UserLogin.this,"Check your Email to verify your account!", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(UserLogin.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
