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
import android.widget.Toast;

import com.example.gitcosattenanceapp.Admin_Logged_in;
import com.example.gitcosattenanceapp.Enquiry_Page;
import com.example.gitcosattenanceapp.HomeActivity;
import com.example.gitcosattenanceapp.Login_Activity_Admin;
import com.example.gitcosattenanceapp.Login_Activity_Employee;
import com.example.gitcosattenanceapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Employee_settings_fragment extends Fragment {

    FirebaseAuth mAuth;
    Button logout_button;

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employee_settings_fragment, container, false);
        logout_button = view.findViewById(R.id.employee_logout_button);
        mAuth = FirebaseAuth.getInstance();
        logout_button.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(getActivity(),"Logout successful",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getActivity(), HomeActivity.class));
            requireActivity().finish();
        });

        return view;


    }
}
