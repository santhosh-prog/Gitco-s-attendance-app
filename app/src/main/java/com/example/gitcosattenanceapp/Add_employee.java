package com.example.gitcosattenanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class Add_employee extends AppCompatActivity {

    EditText  name, role, branch, salary, phone;
    Button addEmployee;
    String sName, sRole, sBranch, sSalary, sPhone;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);

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

        assert currentUser != null;
        String owner=currentUser.getUid();

        ReadWriteEmployeeDetails writeEmployeeDetails=new ReadWriteEmployeeDetails( sName, sRole, sBranch, sSalary, sPhone,owner);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Registered Employees");

        databaseReference.child(sPhone).setValue(writeEmployeeDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                   Toast.makeText(Add_employee.this,"Employee register successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Add_employee.this, Admin_Logged_in.class));

                    progressBar.setVisibility(View.GONE);
                    finish();
                }else{
                    Toast.makeText(Add_employee.this,"registration not successful "+ Objects.requireNonNull(task.getException()),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });

    }

    private void SendSms(){

        sName = name.getText().toString();
        sPhone = phone.getText().toString();


        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(sPhone,null,"welcome to our organization , You can now use "+sPhone+" as your login number",null,null);
    }

    private void CheckIsEmpty() {



        sName = name.getText().toString();
        sRole = role.getText().toString();
        sBranch = branch.getText().toString();
        sSalary = salary.getText().toString();
        sPhone = phone.getText().toString();


       if(sName.isEmpty()) {
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
            //RegisterEmployee();

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