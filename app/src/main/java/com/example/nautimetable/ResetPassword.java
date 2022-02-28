package com.example.nautimetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
EditText Email;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        auth=FirebaseAuth.getInstance();
        Email= findViewById(R.id.Email_Reset);
    }

    public void Reset(View view) {
        resetPassword();
    }
    private void resetPassword(){
        String email=Email.getText().toString().trim();
        if (!email.equals("")) {
            if (email.endsWith("@stu.unizik.edu.ng")) {
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ResetPassword.this, "Check Your Email to reset your password", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ResetPassword.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Enter a valid Registered School Email", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Email Needed", Toast.LENGTH_SHORT).show();
        }
            }
}