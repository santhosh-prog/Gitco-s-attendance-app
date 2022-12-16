package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import com.example.gitcosattenanceapp.admin_fragment.Admin_Home_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_Notification_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_Requests_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_settings_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin_Logged_in extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Admin_Home_fragment admin_home_fragment=new Admin_Home_fragment();
    Admin_Notification_fragment adminNotificationFragment=new Admin_Notification_fragment();
    Admin_Requests_fragment admin_requests_fragment=new Admin_Requests_fragment();
    Admin_settings_fragment admin_settings_fragment=new Admin_settings_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_logged_in);
        initViews();

    }

    private void initViews() {
        bottomNavigationView=findViewById(R.id.admin_page_bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,admin_home_fragment).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,admin_home_fragment).commit();
                    return true;
                case R.id.notification:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,adminNotificationFragment).commit();
                    return true;
                case R.id.requests:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,admin_requests_fragment).commit();
                    return true;
                case R.id.settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,admin_settings_fragment).commit();
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("are you sure you want to exit ?")
                .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Admin_Logged_in.super.onBackPressed();
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