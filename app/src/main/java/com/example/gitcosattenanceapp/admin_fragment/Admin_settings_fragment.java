package com.example.gitcosattenanceapp.admin_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gitcosattenanceapp.Admin_Login;
import com.example.gitcosattenanceapp.Login_Activity;
import com.example.gitcosattenanceapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_settings_fragment extends Fragment {
    FirebaseAuth mAuth;
    Button logout_button;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        return inflater.inflate(R.layout.admin_settings_fragment, container, false);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_settings_fragment, container, false);
        logout_button=view.findViewById(R.id.logout_button);
        mAuth=FirebaseAuth.getInstance();
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mAuth.signOut();
            startActivity(new Intent(getActivity(),Login_Activity.class));
            }
        });

        return view;
    }


}