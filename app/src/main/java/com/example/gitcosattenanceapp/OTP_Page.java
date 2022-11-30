package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OTP_Page extends AppCompatActivity {

    EditText otp_editText;
    Button otp_submit_button;
    int otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_page);

        initViews();
    }

    private void initViews() {
        otp_editText=findViewById(R.id.otp_input);
        otp_submit_button=findViewById(R.id.submit_OTP);
       try {
           otp = Integer.parseInt(otp_editText.getText().toString());
       }catch (Exception e){
           Log.d("OTP as inteager :",e.toString());

       }
        otp_submit_button=findViewById(R.id.submit_OTP);

        otp_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OTP_Page.this,Registration_page.class));
            }
        });





    }
}