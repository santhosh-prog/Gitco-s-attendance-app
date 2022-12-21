package com.example.gitcosattenanceapp.admin_fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gitcosattenanceapp.Edit_admin_details;
import com.example.gitcosattenanceapp.HomeActivity;
import com.example.gitcosattenanceapp.Login_Activity_Admin;
import com.example.gitcosattenanceapp.R;
import com.example.gitcosattenanceapp.ReadWriteAdminDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Admin_settings_fragment extends Fragment {
    FirebaseAuth mAuth;
    Button editAdminDetails,logout_button;
    TextView name;
    String email;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_setting_fragment, container, false);

        editAdminDetails=view.findViewById(R.id.edit_admin_details);
        logout_button=view.findViewById(R.id.admin_logout_button);
        name=view.findViewById(R.id.admin_name_display);
        mAuth=FirebaseAuth.getInstance();

        editAdminDetails.setOnClickListener(v -> startActivity(new Intent(getActivity(), Edit_admin_details.class)));

        SetName();

        logout_button.setOnClickListener(v -> Logout());

     //   initViews();

        return view;

    }

    private void initViews() {


    }

    private void SetName() {
        FirebaseUser currentUser=mAuth.getCurrentUser();
        assert currentUser != null;
        name.setText(email=currentUser.getEmail());
    }

    private void Logout() {
        mAuth.signOut();
        Toast.makeText(getActivity(),"Logout successful",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), HomeActivity.class));
        requireActivity().finish();
    }
}