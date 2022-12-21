package com.example.gitcosattenanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Edit_admin_details extends AppCompatActivity {

    EditText editOrganizationName,editAdminName,editPhoneNumber,totalEmp;
    Button updateButton;
    String orgName,name,phone,totalEmployee;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_admin_details);

        initViews();

    }

    private void initViews() {
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        editOrganizationName=findViewById(R.id.update_organization_name);
        editAdminName=findViewById(R.id.update_admin_name);
        editPhoneNumber=findViewById(R.id.update_phone_number);
        totalEmp=findViewById(R.id.update_total_employee);
        updateButton=findViewById(R.id.update_button);
        progressBar=findViewById(R.id.pb);


        ShowProfile(currentUser);


        updateButton.setOnClickListener(v -> Update());



    }

    private void Update() {

        orgName=editOrganizationName.getText().toString();
        name=editAdminName.getText().toString();
        phone=editPhoneNumber.getText().toString();
        totalEmployee=totalEmp.getText().toString();


        if (orgName.isEmpty()){
            Toast.makeText(this, "Enter organization name", Toast.LENGTH_SHORT).show();
            editOrganizationName.setError("Enter organization name");
            editOrganizationName.requestFocus();
        }else if(name.isEmpty()){
            Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show();
            editAdminName.setError("Enter name");
            editAdminName.requestFocus();
        }else if(phone.isEmpty()){
            Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show();
            editAdminName.setError("Enter phone number");
            editAdminName.requestFocus();
        }
        else if(totalEmployee.isEmpty()){
            Toast.makeText(this, "Enter total employee", Toast.LENGTH_SHORT).show();
            totalEmp.setError("Enter phone number");
            totalEmp.requestFocus();
        } else{
            ReadWriteAdminDetails writeAdminDetails=new ReadWriteAdminDetails(orgName,name,phone,totalEmployee);
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Registered admins");
            String email=currentUser.getEmail();
            assert email != null;
            String childId=email.replace(".",",");

            databaseReference.child(childId).setValue(writeAdminDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().
                            setDisplayName(name).build();
                    currentUser.updateProfile(userProfileChangeRequest);
                    Toast.makeText(Edit_admin_details.this, "update successful", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(Edit_admin_details.this,Admin_Logged_in.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }else{
                    try {
                        throw Objects.requireNonNull(task.getException());
                    }catch (Exception e){
                        Toast.makeText(Edit_admin_details.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                }
            });
        }
    }

    private void ShowProfile(FirebaseUser currentUser) {

        String currentUserUid=currentUser.getUid();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Registered admins");
        progressBar.setVisibility(View.VISIBLE);
       String semail=currentUser.getEmail();
        assert semail != null;
        String childId=semail.replace(".",",");

        databaseReference.child(childId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteAdminDetails readAdminDetails=snapshot.getValue(ReadWriteAdminDetails.class);
                if(readAdminDetails!=null){

                    orgName=readAdminDetails.Organization_Name;
                    name=readAdminDetails.Admin_Name;
                    phone=readAdminDetails.Admin_Mobile_Number;
                    totalEmployee=readAdminDetails.Total_Employees;


                    editOrganizationName.setText(orgName);
                    editAdminName.setText(name);
                    editPhoneNumber.setText(phone);
                    totalEmp.setText(totalEmployee);


                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Edit_admin_details.this, "Something went wrong "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}