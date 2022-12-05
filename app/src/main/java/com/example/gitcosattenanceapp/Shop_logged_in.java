package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Shop_logged_in extends AppCompatActivity {

    Button logout_button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_logged_in);

        logout_button=findViewById(R.id.shop_logout);
        mAuth=FirebaseAuth.getInstance();

        logout_button.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(Shop_logged_in.this,"Logged out successful",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Shop_logged_in.this, Login_Activity_Shop.class));
            finish();
        });
    }
}