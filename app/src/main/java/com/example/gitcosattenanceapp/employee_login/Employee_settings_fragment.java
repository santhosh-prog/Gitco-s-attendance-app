package com.example.gitcosattenanceapp.employee_login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gitcosattenanceapp.Login_Activity_Admin;
import com.example.gitcosattenanceapp.Login_Activity_Employee;
import com.example.gitcosattenanceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


public class Employee_settings_fragment extends Fragment {

    FirebaseAuth mAuth;
    Button logout_button;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employee_settings_fragment, container, false);
        logout_button = view.findViewById(R.id.employee_logout_button);
        mAuth = FirebaseAuth.getInstance();
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), Login_Activity_Employee.class));
            }
        });

        return view;
    }
}
