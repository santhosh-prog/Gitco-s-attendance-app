package com.example.gitcosattenanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Login_Activity extends AppCompatActivity {

    EditText email_login_editText;
    EditText password_login_editText;
    Button login_Button;
    TextView dontHaveAccount;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth=FirebaseAuth.getInstance();
        email_login_editText=findViewById(R.id.login_page_email_input);
        password_login_editText=findViewById(R.id.loginPassword);
        login_Button=findViewById(R.id.login_page_button);
        dontHaveAccount=findViewById(R.id.dontHaveAccount);

        dontHaveAccount.setOnClickListener(v -> {
            startActivity(new Intent(Login_Activity.this,Register_page.class));
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
            Toast.makeText(this, "please enter your email ID", Toast.LENGTH_LONG).show();
        }else if(login_password_input.isEmpty()){
            Toast.makeText(this, "please enter your password", Toast.LENGTH_LONG).show();
        }
        else if(login_password_input.length()<6) {
            Toast.makeText(this, "password must contain minimum 6 character", Toast.LENGTH_LONG).show();
        }else{
            mAuth.signInWithEmailAndPassword(login_email_input,login_password_input).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login_Activity.this,"Login successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login_Activity.this,Admin_Login.class));
                    }else{
                        Toast.makeText(Login_Activity.this,"Login not successful",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}