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
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    EditText email_input_to_change_password_editText;
    Button get_mail_button;
    TextView back_to_login_textView;
    String email;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        initViews();
    }

    private void initViews() {
        email_input_to_change_password_editText=findViewById(R.id.input_email_to_change_password);
        get_mail_button=findViewById(R.id.get_changePassword_email_button);
        back_to_login_textView=findViewById(R.id.backtologin);
        mAuth=FirebaseAuth.getInstance();

        back_to_login_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forgot_Password.this, Login_Activity_Admin.class));
            }
        });

        get_mail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=email_input_to_change_password_editText.getText().toString();

                if(email.isEmpty()){
                    email_input_to_change_password_editText.setError("Must enter Email");
                }else{
                    forgetPassword();
                }

            }
        });

    }

    private void forgetPassword() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Forgot_Password.this,"Check your mail",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Forgot_Password.this, Login_Activity_Admin.class));
                    finish();
                }else
                    Toast.makeText(Forgot_Password.this,"error :"+task.getException().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}