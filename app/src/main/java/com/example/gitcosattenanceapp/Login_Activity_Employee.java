package com.example.gitcosattenanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Login_Activity_Employee extends AppCompatActivity {

    EditText email_login_editText;
    EditText password_login_editText;
    Button login_Button;
    TextView forgot_Password;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_employee);

        email_login_editText=findViewById(R.id.employee_login_page_email_input);
        password_login_editText=findViewById(R.id.employee_loginPassword);
        login_Button=findViewById(R.id.employee_login_page_button);
        forgot_Password=findViewById(R.id.employee_forgotPassword);

        mAuth=FirebaseAuth.getInstance();

        forgot_Password.setOnClickListener(v -> {
            startActivity(new Intent(Login_Activity_Employee.this,Forgot_Password.class));
        });


        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();
            }
        });


    }

    private void LoginUser() {
        String login_email_input=email_login_editText.getText().toString();
        String login_password_input=password_login_editText.getText().toString();
        if(login_email_input.isEmpty()){
            email_login_editText.setError("Enter your email");
            email_login_editText.requestFocus();
        }else if(login_password_input.isEmpty()){
            password_login_editText.setError("Enter your password");
            password_login_editText.requestFocus();
        }
        else if(login_password_input.length()<6) {
            password_login_editText.setError("password must contain 6 characters or more");
            password_login_editText.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(login_email_input,login_password_input).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login_Activity_Employee.this,"Login successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login_Activity_Employee.this, Employee_Logged_in.class));
                        finishAffinity();
                    }else{
                        Toast.makeText(Login_Activity_Employee.this,"Login not successful",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}