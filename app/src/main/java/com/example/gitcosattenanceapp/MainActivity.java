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

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    Button sendOtp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.input_mob_no);
        sendOtp =findViewById(R.id.btnsend);
        progressBar=findViewById(R.id.probar1);

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText1.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }




                else if ((editText1.getText().toString().trim()).length()!=10) {
                    Toast.makeText(MainActivity.this, "Please enter correct Number", Toast.LENGTH_SHORT).show();

                }
                else {


                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference userNameRef = rootRef.child("Registered Employees").child("+91"+editText1.getText().toString());
                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.exists()) {
                                Toast.makeText(MainActivity.this, "you are not working under any organization", Toast.LENGTH_SHORT).show();

                            }else{
                                SendVerificationCode();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d(TAG, databaseError.getMessage());
                        }
                    };
                    userNameRef.addListenerForSingleValueEvent(eventListener);


                }



            }
        });



    }

    private void SendVerificationCode() {
        progressBar.setVisibility(View.VISIBLE);
        sendOtp.setVisibility(View.INVISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + editText1.getText().toString(),
                60,
                TimeUnit.SECONDS,
                MainActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                        progressBar.setVisibility(View.GONE);
                        sendOtp.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                        progressBar.setVisibility(View.GONE);
                        sendOtp.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                        progressBar.setVisibility(View.GONE);
                        sendOtp.setVisibility(View.VISIBLE);

                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        intent.putExtra("mobile", editText1.getText().toString());
                        intent.putExtra("backendotp", backendotp);
                        startActivity(intent);

                    }
                }

        );
    }
}