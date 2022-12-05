package com.example.gitcosattenanceapp.admin_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gitcosattenanceapp.Login_Activity_Admin;
import com.example.gitcosattenanceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Admin_Home_fragment extends Fragment {

    FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin__home_fragment, container, false);
        mAuth=FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();

        if(user==null){
            startActivity(new Intent(getActivity(), Login_Activity_Admin.class));
        }

    }
}