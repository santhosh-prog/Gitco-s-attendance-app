package com.example.gitcosattenanceapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Enquiry_Page extends AppCompatActivity {

    EditText organizationName_editText;
    EditText adminName_editText;
    EditText emailID_editText;
    EditText total_employee_editText;
    Button admin_register_button;
    String organizationName,adminName, phone, total_employee,email,childId;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enquiry_page);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        initViews();
    }

    private void initViews() {
        organizationName_editText=findViewById(R.id.organization_name_input);
        adminName_editText=findViewById(R.id.admin_name_input);
        emailID_editText=findViewById(R.id.phone_num);
        total_employee_editText=findViewById(R.id.total_employees_input);
        admin_register_button=findViewById(R.id.register_admin_button);
        progressBar=findViewById(R.id.enquiryProgressBar);

        progressBar.setVisibility(View.GONE);

        validate();
    }

    private void validate() {

            admin_register_button.setOnClickListener(v -> IsEmpty());
    }
    private void IsEmpty() {
        organizationName=organizationName_editText.getText().toString();
        adminName=adminName_editText.getText().toString();
        phone =emailID_editText.getText().toString();
        total_employee=total_employee_editText.getText().toString();

        if(organizationName.isEmpty()||adminName.isEmpty()|| phone.isEmpty()||total_employee.isEmpty()){

            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            //Adding data to database
            progressBar.setVisibility(View.VISIBLE);
            FirebaseUser currentUser=mAuth.getCurrentUser();
            assert currentUser != null;
            email=currentUser.getEmail();
            assert email != null;
            childId=email.replace(".",",");
            ReadWriteAdminDetails writeAdminDetails=new ReadWriteAdminDetails(organizationName,adminName, phone, total_employee);

            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Registered admins");
            databaseReference.child(Objects.requireNonNull(childId)).setValue(writeAdminDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(Enquiry_Page.this,"register successful ",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Enquiry_Page.this, Admin_Logged_in.class));
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }else{
                        Toast.makeText(Enquiry_Page.this,"registration not successful "+ Objects.requireNonNull(task.getException()).toString(),Toast.LENGTH_LONG).show();
                    }

                }
            });



        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(Enquiry_Page.this,"Registration failed ",Toast.LENGTH_LONG).show();

        deleteAccount();
    }



    private void deleteAccount() {
        Log.d(TAG, " deleteAccount");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        assert currentUser != null;
        currentUser.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG,"OK! Works fine!");
                Toast.makeText(Enquiry_Page.this,"Registration failed",Toast.LENGTH_LONG).show();
            } else {
                Log.w(TAG,"Something is wrong!");
            }
        });
    }
}