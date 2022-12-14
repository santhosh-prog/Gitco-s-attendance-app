package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Shop_logged_in extends AppCompatActivity {

    Button punch_in_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_logged_in);

        punch_in_out=findViewById(R.id.shop_punch_in_out);

       punch_in_out.setOnClickListener(v -> {
           PopupMenu popupMenu = new PopupMenu(Shop_logged_in.this, punch_in_out);

           popupMenu.getMenuInflater().inflate(R.menu.attendance_popup, popupMenu.getMenu());

           popupMenu.setOnMenuItemClickListener(item -> {

               if(item.getTitle().equals("Via photo")){
                   Toast.makeText(Shop_logged_in.this, "You have preferred photo input", Toast.LENGTH_SHORT).show();
               }
               else if(item.getTitle().equals("Via biometric")){
                   Toast.makeText(Shop_logged_in.this, "You have preferred biometric input", Toast.LENGTH_SHORT).show();
               }
               return true;

           });
           popupMenu.show();
       });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,Login_Activity_Shop.class));
    }
}