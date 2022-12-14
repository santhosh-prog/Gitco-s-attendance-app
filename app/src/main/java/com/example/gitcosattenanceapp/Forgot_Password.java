package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Forgot_Password extends AppCompatActivity {

    EditText email_input_to_change_password_editText;
    Button get_mail_button,go_back;
    String email;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        initViews();
    }

    private void initViews() {
        go_back=findViewById(R.id.psd_pg_go_back_btn);
        email_input_to_change_password_editText=findViewById(R.id.input_email_to_change_password);
        get_mail_button=findViewById(R.id.get_changePassword_email_button);
        go_back.setBackgroundColor(Color.TRANSPARENT);
        mAuth=FirebaseAuth.getInstance();

        go_back.setOnClickListener(v -> {
            startActivity(new Intent(Forgot_Password.this, Login_Activity_Admin.class));
            finish();
        });

        get_mail_button.setOnClickListener(v -> {
            email=email_input_to_change_password_editText.getText().toString();

            if(email.isEmpty()){
                email_input_to_change_password_editText.setError("Must enter Email");
            }else{
                forgetPassword();
            }

        });

    }
    private void forgetPassword() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(Forgot_Password.this,"Check your mail",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Forgot_Password.this, Login_Activity_Admin.class));
                finish();
            }else
                Toast.makeText(Forgot_Password.this,"error :"+ Objects.requireNonNull(task.getException()),Toast.LENGTH_LONG).show();
        });

    }
}