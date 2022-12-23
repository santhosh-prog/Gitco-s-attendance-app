package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button admin,employee,shop,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        admin=findViewById(R.id.abtn);
        employee=findViewById(R.id.b2);
        shop=findViewById(R.id.b3);
        signup=findViewById(R.id.home_signup);


        {
            admin.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, Login_Activity_Admin.class)));
        }
        {
            employee.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, MainActivity.class)));
        }
        {
            shop.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, Login_Activity_Shop.class)));
        }
        {
            signup.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, Register_page.class)));
        }


    }
}