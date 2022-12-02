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

public class Register_page extends AppCompatActivity {

    EditText EmailID;
    EditText password;
    Button register_button;
    String emailId_Input,password_input;
    TextView alreadyHaveAccount;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        mAuth=FirebaseAuth.getInstance();

        initViews();
    }

    private void initViews() {
        EmailID =findViewById(R.id.email_input);
        password=findViewById(R.id.registrationPassword);
        register_button =findViewById(R.id.Register_page_button);
        alreadyHaveAccount=findViewById(R.id.alreadyHaveAccount);

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_page.this,Login_Activity.class));
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });
    }

    private void Check() {

        emailId_Input = EmailID.getText().toString();
        password_input= password.getText().toString();
        if(emailId_Input.isEmpty()){
            EmailID.setError("enter email id to register");
            EmailID.requestFocus();
        }else if(password_input.isEmpty()){
            password.setError("must enter a password");
            password.requestFocus();
        }
        else if(password_input.length()<6){
            password.setError("password must contain at least 6 characters");
            password.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(emailId_Input,password_input).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register_page.this,"register successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Register_page.this,Enquiry_Page.class));
                        finish();
                    }else{
                        Toast.makeText(Register_page.this,"register not successful",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}