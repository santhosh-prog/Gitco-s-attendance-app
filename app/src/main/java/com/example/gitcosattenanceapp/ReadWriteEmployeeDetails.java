package com.example.gitcosattenanceapp;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ReadWriteEmployeeDetails {
   public String  Employee_Name,  Employee_Role,  Employee_Branch,  Employee_Salary,  Employee_Phone , Owner;


    public ReadWriteEmployeeDetails(String sName,String sRole,String sBranch,String sSalary,String sPhone,String owner){



        this.Employee_Name=sName;
        this.Employee_Role=sRole;
        this.Employee_Branch=sBranch;
        this.Employee_Salary=sSalary;
        this.Employee_Phone=sPhone;
        this.Owner=owner;
    }

    public ReadWriteEmployeeDetails(){

    }
}
