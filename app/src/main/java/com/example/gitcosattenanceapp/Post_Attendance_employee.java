package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Post_Attendance_employee extends AppCompatActivity {

    String punch;
    TextView back_to_emp_home;
    Button punch_in_out_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_attendance);
        punch="punch in";
        back_to_emp_home=findViewById(R.id.pst_att_to_emp_home);
        punch_in_out_button=findViewById(R.id.punch_in_out);

        punch_in_out_button.setText(punch);

        punch_in_out_button.setOnClickListener(v -> {
            if(punch_in_out_button.getText()==punch){
                punch_in_out_button.setText("punch out");
            }
            else if (punch_in_out_button.getText()=="punch out"){
                punch_in_out_button.setText("punch in");
            }
        });

        back_to_emp_home.setOnClickListener(v -> {
            startActivity(new Intent(Post_Attendance_employee.this,Employee_Logged_in.class));
            finish();
        });

    }
}