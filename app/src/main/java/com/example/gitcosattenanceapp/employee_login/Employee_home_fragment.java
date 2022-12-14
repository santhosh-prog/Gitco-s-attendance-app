package com.example.gitcosattenanceapp.employee_login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gitcosattenanceapp.Post_Attendance_employee;
import com.example.gitcosattenanceapp.R;

public class Employee_home_fragment extends Fragment {

    Button post_attendance;

    @SuppressLint("MissingInflatedId")

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.employee_home_fragment , container, false);

        post_attendance=view.findViewById(R.id.employee_post_attendance_button);

        post_attendance.setOnClickListener(v -> startActivity(new Intent(getActivity(), Post_Attendance_employee.class)));

        return view;
    }
}