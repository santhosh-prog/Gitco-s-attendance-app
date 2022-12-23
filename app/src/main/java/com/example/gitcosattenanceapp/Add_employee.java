package com.example.gitcosattenanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Add_employee extends AppCompatActivity {

    EditText email, password, name, role, branch, salary, phone;
    Button addEmployee;
    String sEmail, sPassword, sName, sRole, sBranch, sSalary, sPhone;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);
        email = findViewById(R.id.e1);
        password = findViewById(R.id.e2);
        name = findViewById(R.id.e3);
        role = findViewById(R.id.e4);
        branch = findViewById(R.id.e5);
        salary = findViewById(R.id.e6);
        phone = findViewById(R.id.e7);
        addEmployee = findViewById(R.id.add_employee_by_admin);
        progressBar=findViewById(R.id.regEmpPb);
        progressBar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();

        initViews();

    }

    private void initViews() {



addEmployee.setOnClickListener(v -> CheckIsEmpty());

    }

    private void AddToDb() {

        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser currentUser=mAuth.getCurrentUser();

        ReadWriteEmployeeDetails writeEmployeeDetails=new ReadWriteEmployeeDetails(sEmail, sPassword, sName, sRole, sBranch, sSalary, sPhone);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Registered Employees");

        assert currentUser != null;
        databaseReference.child(Objects.requireNonNull(currentUser.getUid())).child(sName).setValue(writeEmployeeDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    currentUser.sendEmailVerification();
                   // Toast.makeText(Add_employee.this,"register successful, please verify your email Id ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Add_employee.this, Admin_Logged_in.class));
                    progressBar.setVisibility(View.GONE);
                    finish();
                }else{
                    Toast.makeText(Add_employee.this,"registration not successful "+ Objects.requireNonNull(task.getException()),Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void RegisterEmployee() {

       progressBar.setVisibility(View.VISIBLE);
        sEmail = email.getText().toString();
        sPassword = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Add_employee.this,"Employee Registered successfully",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    if(ContextCompat.checkSelfPermission(Add_employee.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED) {
                    SendSms();
                    }else{
                        ActivityCompat.requestPermissions(Add_employee.this,new String[]{Manifest.permission.SEND_SMS},100);
                    }
                        finish();
                }else{
                    Toast.makeText(Add_employee.this,"Employee registration not successful"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void SendSms(){
        sEmail = email.getText().toString();
        sName = name.getText().toString();
        sPhone = phone.getText().toString();
        sPassword = password.getText().toString();

        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(sPhone,null,"welcome "+sName+"\n"+"your Id is : "+sEmail+"\n"+"Your password is :"+sPassword,null,null);
    }

    private void CheckIsEmpty() {


        sEmail = email.getText().toString();
        sPassword = password.getText().toString();
        sName = name.getText().toString();
        sRole = role.getText().toString();
        sBranch = branch.getText().toString();
        sSalary = salary.getText().toString();
        sPhone = phone.getText().toString();

        if(sEmail.isEmpty()){
            Toast.makeText(this, "enter Email id", Toast.LENGTH_SHORT).show();
            email.setError("enter Email id");
            email.requestFocus();
        }else if(sPassword.isEmpty()) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
            password.setError("enter password");
            password.requestFocus();
        }
        else if(sPassword.length()<6) {
            Toast.makeText(this, "password must contain at least 6 characters", Toast.LENGTH_SHORT).show();
            password.setError("password must contain at least 6 characters");
            password.requestFocus();
        }else if(sName.isEmpty()) {
            Toast.makeText(this, "enter Name", Toast.LENGTH_SHORT).show();
            name.setError("enter name");
            name.requestFocus();
        }else if(sRole.isEmpty()) {
            Toast.makeText(this, "enter Role", Toast.LENGTH_SHORT).show();
            role.setError("enter Role");
            role.requestFocus();
        }
        else if(sBranch.isEmpty()) {
            Toast.makeText(this, "enter Branch", Toast.LENGTH_SHORT).show();
            branch.setError("enter branch");
            branch.requestFocus();
        }
        else if(sSalary.isEmpty()) {
            Toast.makeText(this, "enter Salary", Toast.LENGTH_SHORT).show();
            salary.setError("enter salary");
            salary.requestFocus();
        }
        else if(sPhone.isEmpty()) {
            Toast.makeText(this, "enter phone number", Toast.LENGTH_SHORT).show();
            phone.setError("enter phone Number");
            phone.requestFocus();
        }else{
            RegisterEmployee();

            AddToDb();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {

            SendSms();
        }else{
            Toast.makeText(getApplicationContext(),"permission denied!",Toast.LENGTH_SHORT).show();
        }
    }
}