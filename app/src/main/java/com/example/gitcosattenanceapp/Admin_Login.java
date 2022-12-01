package com.example.gitcosattenanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gitcosattenanceapp.admin_fragment.Admin_Home_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_Notification_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_Requests_fragment;
import com.example.gitcosattenanceapp.admin_fragment.Admin_settings_fragment;
import com.example.gitcosattenanceapp.databinding.AdminLoginBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarView;

public class Admin_Login extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Admin_Home_fragment admin_home_fragment=new Admin_Home_fragment();
    Admin_Notification_fragment adminNotificationFragment=new Admin_Notification_fragment();
    Admin_Requests_fragment admin_requests_fragment=new Admin_Requests_fragment();
    Admin_settings_fragment admin_settings_fragment=new Admin_settings_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

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

}