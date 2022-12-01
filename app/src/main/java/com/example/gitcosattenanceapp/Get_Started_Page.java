package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Get_Started_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_started_page);
    }

    public void Go_To_mobile_verification_page(View view) {
        startActivity(new Intent(Get_Started_Page.this, Register_page.class));
    }
}