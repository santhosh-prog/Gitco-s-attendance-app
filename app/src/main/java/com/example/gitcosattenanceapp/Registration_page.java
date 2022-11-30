package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration_page extends AppCompatActivity {

    EditText organizationName_editText;
    EditText adminName_editText;
    EditText emailID_editText;
    EditText total_employee_editText;
    Button admin_register_button;
    String organizationName,adminName,emailID, total_employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);

        initViews();
    }

    private void initViews() {
        organizationName_editText=findViewById(R.id.organization_name_input);
        adminName_editText=findViewById(R.id.admin_name_input);
        emailID_editText=findViewById(R.id.email_ID_input);
        total_employee_editText=findViewById(R.id.total_employees_input);
        admin_register_button=findViewById(R.id.register_admin_button);

        validate();
    }

    private void validate() {


            admin_register_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IsEmpty();
                }
            });
    }

    private void IsEmpty() {
        organizationName=organizationName_editText.getText().toString();
        adminName=adminName_editText.getText().toString();
        emailID=emailID_editText.getText().toString();
        total_employee=total_employee_editText.getText().toString();

        if(organizationName.isEmpty()||adminName.isEmpty()||emailID.isEmpty()||total_employee.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(Registration_page.this,Admin_Login.class));
        }
    }
}