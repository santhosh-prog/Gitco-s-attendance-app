package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Phone_Number_Verification_Page extends AppCompatActivity {

    EditText mobile_editText;
    Button next_button;
    Long number;
    String mobileNumber_String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_number_verification_page);

        initViews();
    }

    private void initViews() {
        mobile_editText=findViewById(R.id.mobile_number_input);
        next_button=findViewById(R.id.mobileNumber_page_next_button);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckNumber();
            }
        });
    }

    private void CheckNumber() {

        mobileNumber_String=mobile_editText.getText().toString();
        if(mobileNumber_String.isEmpty()){
            Toast.makeText(this, "please enter your phone number", Toast.LENGTH_LONG).show();
        }else {
            number = Long.parseLong(mobile_editText.getText().toString());

             if (mobileNumber_String.length() != 10) {
                Toast.makeText(this, "Please check the number", Toast.LENGTH_LONG).show();
            } else if (mobileNumber_String.startsWith("1") || mobileNumber_String.startsWith("2") || mobileNumber_String.startsWith("3") || mobileNumber_String.startsWith("4") || mobileNumber_String.startsWith("5")
                    || mobileNumber_String.startsWith("0")) {
                Toast.makeText(this, "Please check the number", Toast.LENGTH_LONG).show();
            } else {
                startActivity(new Intent(Phone_Number_Verification_Page.this, OTP_Page.class));
            }
        }

    }
}