package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Get_Started_Page extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_started_page);

        button=findViewById(R.id.get_started_button);

        button.setOnClickListener(v -> startActivity(new Intent(Get_Started_Page.this,HomeActivity.class)));
    }


}