package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button admin,employee,shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        admin=findViewById(R.id.abtn);
        employee=findViewById(R.id.b2);
        shop=findViewById(R.id.b3);



        {
            admin.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, Login_Activity_Admin.class)));
        }
        {
            employee.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, Login_Activity_Employee.class)));
        }
        {
            shop.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, Login_Activity_Shop.class)));
        }



    }
}