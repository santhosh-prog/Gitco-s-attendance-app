package com.example.gitcosattenanceapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Login_Activity_Employee extends AppCompatActivity {
    EditText phone, otp;
    Button btngenOTP, btnverify;
    FirebaseAuth mAuth;
    String verificationID;
    ProgressBar bar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_employee);
        phone = findViewById(R.id.phone);
        otp = findViewById(R.id.otp);
        btngenOTP = findViewById(R.id.btngenerateOTP);
        btnverify = findViewById(R.id.btnverifyOTP);
        mAuth = FirebaseAuth.getInstance();
        bar = findViewById(R.id.bar);
        btngenOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phone.getText().toString())) {
                    Toast.makeText(Login_Activity_Employee.this, "Enter Valid Phone No.", Toast.LENGTH_SHORT).show();
                } else {
                    String number = phone.getText().toString();
                    bar.setVisibility(View.VISIBLE);

                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference userNameRef = rootRef.child("Registered Employees").child(number);
                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.exists()) {
                                Toast.makeText(Login_Activity_Employee.this, "not a valid user ", Toast.LENGTH_SHORT).show();

                            }else{
                                sendverificationcode(number);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
                        }
                    };
                    userNameRef.addListenerForSingleValueEvent(eventListener);


                }
            }
        });
        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(otp.getText().toString())) {
                    Toast.makeText(Login_Activity_Employee.this, "Wrong OTP Entered", Toast.LENGTH_SHORT).show();
                } else
                    verifycode(otp.getText().toString());
            }
        });
    }

    private void sendverificationcode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phoneNumber)  // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code != null) {
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Login_Activity_Employee.this, "Verification Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            verificationID = s;
            Toast.makeText(Login_Activity_Employee.this, "Code sent", Toast.LENGTH_SHORT).show();
            btnverify.setEnabled(true);
            bar.setVisibility(View.INVISIBLE);
        }
    };

    private void verifycode(String Code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, Code);
        signinbyCredentials(credential);
    }

    private void signinbyCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Activity_Employee.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_Activity_Employee.this, Employee_Logged_in.class));
                        }

                    }
                });
    }
}