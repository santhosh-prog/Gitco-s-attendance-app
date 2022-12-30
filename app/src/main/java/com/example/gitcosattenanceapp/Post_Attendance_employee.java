package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Post_Attendance_employee extends AppCompatActivity {

    TextView back_to_emp_home;
    Button punch_in_button , punch_Out_button;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_attendance);

        back_to_emp_home=findViewById(R.id.pst_att_to_emp_home);
        punch_in_button =findViewById(R.id.punch_in_out);
        punch_Out_button =findViewById(R.id.punch_in_out_2);
        mAuth=FirebaseAuth.getInstance();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Attendance");

        punch_in_button.setOnClickListener(v -> {
            LocalDate date = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = LocalDate.now();
            }
            String currentDate=String.valueOf(date);


            FirebaseUser currentUser=mAuth.getCurrentUser();

            assert currentUser != null;
            String phoneNumber=currentUser.getPhoneNumber();

            assert phoneNumber != null;
            ref.child("Date : "+currentDate).child(phoneNumber).child("check In").setValue("checked in");

            punch_in_button.setVisibility(View.GONE);
            punch_Out_button.setVisibility(View.VISIBLE);

        });

        punch_Out_button.setOnClickListener(v -> {

            LocalDate date = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                date = LocalDate.now();
            }
            String currentDate=String.valueOf(date);


            FirebaseUser currentUser=mAuth.getCurrentUser();

            assert currentUser != null;
            String phoneNumber=currentUser.getPhoneNumber();

            assert phoneNumber != null;
            ref.child("Date : "+currentDate).child(phoneNumber).child("check out").setValue("checked Out");

            punch_in_button.setVisibility(View.VISIBLE);
            punch_Out_button.setVisibility(View.GONE);

        });

        back_to_emp_home.setOnClickListener(v -> {
            startActivity(new Intent(Post_Attendance_employee.this,Employee_Logged_in.class));
            finish();
        });

    }
}