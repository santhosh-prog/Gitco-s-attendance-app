package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gitcosattenanceapp.admin_fragment.Admin_Home_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_Notification_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_Requests_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_settings_fragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin_Logged_in extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    Admin_Home_fragment admin_home_fragment=new Admin_Home_fragment();
    Admin_Notification_fragment adminNotificationFragment=new Admin_Notification_fragment();
    Admin_Requests_fragment admin_requests_fragment=new Admin_Requests_fragment();
    Admin_settings_fragment admin_settings_fragment=new Admin_settings_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_logged_in);
        mAuth=FirebaseAuth.getInstance();
        initViews();

    }

    private void initViews() {
        bottomNavigationView=findViewById(R.id.admin_page_bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,admin_home_fragment).commit();
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);
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
    protected void onStart() {
        super.onStart();

        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(Admin_Logged_in.this, Login_Activity_Admin.class));
        }
    }

}