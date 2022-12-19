package com.example.gitcosattenanceapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.PasswordAuthentication;

public class ReadWriteEmployeeDetails {
    public String Employee_Email,  Employee_Password,  Employee_Name,  Employee_Role,  Employee_Branch,  Employee_Salary,  Employee_Phone;

    public ReadWriteEmployeeDetails(String sEmail,String sPassword,String sName,String sRole,String sBranch,String sSalary,String sPhone){

        this.Employee_Email=sEmail;
        this.Employee_Password=sPassword;
        this.Employee_Name=sName;
        this.Employee_Role=sRole;
        this.Employee_Branch=sBranch;
        this.Employee_Salary=sSalary;
        this.Employee_Phone=sPhone;
    }
}
