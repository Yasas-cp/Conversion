package com.example.conversions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView textView8, regUser;
    private EditText textFullName, textAge, textEmailAddress, textPassword;

    /* Initialising Firebase Authentication */
    private FirebaseAuth mAuth;

    public RegisterUser() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        /* Setting OnClick Listeners */
        textView8 = (TextView) findViewById(R.id.textView8);
        textView8.setOnClickListener(this);

        regUser = (Button) findViewById(R.id.regUser);
        regUser.setOnClickListener(this);

        textFullName = (EditText) findViewById(R.id.fullname);
        textAge = (EditText) findViewById(R.id.age);
        textEmailAddress = (EditText) findViewById(R.id.user);
        textPassword = (EditText) findViewById(R.id.pass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView8:
                startActivity(new Intent(this, UserLogin.class));
                break;
            case R.id.regUser:
                regUser();
                break;
        }

    }

    private void regUser() {
        String Fullname = textFullName.getText().toString().trim();
        String Age = textAge.getText().toString().trim();
        String Email = textEmailAddress.getText().toString().trim();
        String Password = textPassword.getText().toString().trim();

        if(Fullname.isEmpty()){
            textFullName.setError("Full name is required");
            textFullName.requestFocus();
            return;
        }
        if(Age.isEmpty()){
            textAge.setError("Age is required");
            textAge.requestFocus();
            return;
        }
        if(Email.isEmpty()){
            textEmailAddress.setError("Email is required");
            textEmailAddress.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            textEmailAddress.setError("Enter Valid Email please!");
            textEmailAddress.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            textPassword.setError("Password is required");
            textPassword.requestFocus();
            return;
        }
        if(Password.length() < 6){
            textPassword.setError("Minimum length of password should be 6 characters!");
            textPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(Fullname, Age, Email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has been Registered Successfully!", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterUser.this, "Registration Failed! Try Again.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(RegisterUser.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}