package com.example.gitcosattenanceapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ReadWriteAdminDetails {

    public String Organization_Name,Admin_Name, Admin_Mobile_Number, Total_Employees,email;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseUser currentUser=mAuth.getCurrentUser();


    public ReadWriteAdminDetails(String organizationName, String adminName, String phone, String total_employee){
        this.Organization_Name =organizationName;
        this.Admin_Name=adminName;
        this.Admin_Mobile_Number=phone;
        this.Total_Employees =total_employee;
        assert currentUser != null;
        email = currentUser.getEmail();
    }
}

