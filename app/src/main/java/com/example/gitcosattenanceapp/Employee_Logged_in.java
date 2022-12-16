package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.example.gitcosattenanceapp.employee_login.Employee_home_fragment;
import com.example.gitcosattenanceapp.employee_login.Employee_notification_fragment;
import com.example.gitcosattenanceapp.employee_login.Employee_requests_fragment;
import com.example.gitcosattenanceapp.employee_login.Employee_settings_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Employee_Logged_in extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    Employee_home_fragment employee_home_fragment=new Employee_home_fragment();
    Employee_notification_fragment employee_notification_fragment=new Employee_notification_fragment();
    Employee_requests_fragment employee_requests_fragment=new Employee_requests_fragment();
    Employee_settings_fragment employee_settings_fragment=new Employee_settings_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_logged_in);
        mAuth=FirebaseAuth.getInstance();

        initViews();

    }

    private void initViews() {
        bottomNavigationView=findViewById(R.id.employee_pg_btm_navbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.employee_container,employee_home_fragment).commit();


        try{

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.employee_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.employee_container,employee_home_fragment).commit();
                    return true;
                case R.id.employee_notification:
                    getSupportFragmentManager().beginTransaction().replace(R.id.employee_container,employee_notification_fragment).commit();
                    return true;
                case R.id.employee_request:
                    getSupportFragmentManager().beginTransaction().replace(R.id.employee_container,employee_requests_fragment).commit();
                    return true;
                case R.id.employee_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.employee_container,employee_settings_fragment).commit();
                    return true;
            }
            return false;
        });}catch (Exception e){
            Log.e("MYAPP", "exception", e);
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("are you sure you want to exit ?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Employee_Logged_in.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();


    }
}