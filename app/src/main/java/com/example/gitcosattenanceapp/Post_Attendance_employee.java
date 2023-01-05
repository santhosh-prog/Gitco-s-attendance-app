package com.example.gitcosattenanceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.biometric.BiometricPrompt;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.Executor;

public class Post_Attendance_employee extends AppCompatActivity {

    TextView back_to_emp_home;
    Button punch_in_button, punch_Out_button;
    ImageView imageView;
    FirebaseAuth mAuth;
    DatabaseReference dbRef;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private Uri filePath=null;
    private StorageReference storageReference=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_attendance);

        imageView = findViewById(R.id.selfie);
        punch_in_button = findViewById(R.id.punch_in_out);
        punch_Out_button = findViewById(R.id.punch_in_out_2);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("Attendance");

        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();



        punch_in_button.setOnClickListener(v -> {

            BiometricManager biometricManager = BiometricManager.from(this);
            switch (biometricManager.canAuthenticate()) {
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    NotifyUser("your device does not have fingerPrint Sensor");
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    NotifyUser("Not Working");
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    NotifyUser("No fingerPrint Assigned");
            }

            Executor executor = ContextCompat.getMainExecutor(this);

            biometricPrompt = new BiometricPrompt(Post_Attendance_employee.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    NotifyUser(errString.toString());
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    checkCameraPermission();

                   // CheckIn();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    NotifyUser("Authentication failed");
                }
            });
            promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Give attendance")
                    .setDescription("use your finger Print to attendance").setNegativeButtonText("Cancel").setDeviceCredentialAllowed(false).build();
            biometricPrompt.authenticate(promptInfo);

        });

        punch_Out_button.setOnClickListener(v -> {


            BiometricManager biometricManager = BiometricManager.from(this);
            switch (biometricManager.canAuthenticate()) {
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    NotifyUser("your device does not have fingerPrint Sensor");
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    NotifyUser("Not Working");
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    NotifyUser("No fingerPrint Assigned");
            }
            Executor executor = ContextCompat.getMainExecutor(this);

            biometricPrompt = new BiometricPrompt(Post_Attendance_employee.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    NotifyUser(errString.toString());
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);

                   CheckOut();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    NotifyUser("Authentication failed");
                }
            });
            promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Give attendance")
                    .setDescription("use your finger Print to attendance").setNegativeButtonText("cancel").setDeviceCredentialAllowed(false).build();
            biometricPrompt.authenticate(promptInfo);

        });


    }


    private void CheckOut() {
        LocalDate date = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = LocalDate.now();
        }
        String currentDate = String.valueOf(date);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser != null;
        String phoneNumber = currentUser.getPhoneNumber();
        assert phoneNumber != null;
        String txt = "checked Out";
        String now = String.valueOf(new Date());
        dbRef.child("Date : " + currentDate).child(phoneNumber).child("checked Out at").setValue(now);
        NotifyUser("you are checked Out successfully ");
        punch_in_button.setVisibility(View.VISIBLE);
        punch_Out_button.setVisibility(View.GONE);
    }

    private void checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            }
            else
            {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
    }


    private void CheckIn() {
        LocalDate date = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = LocalDate.now();
        }
        String currentDate = String.valueOf(date);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser != null;
        String phoneNumber = currentUser.getPhoneNumber();
        assert phoneNumber != null;
        String now = String.valueOf(new Date());
        dbRef.child("Date : " + currentDate).child(phoneNumber).child("checked In at").setValue(now);
//        checkCameraPermission();
        NotifyUser("you are checked in successfully ");
        punch_in_button.setVisibility(View.GONE);
        punch_Out_button.setVisibility(View.VISIBLE);
    }

    private void UploadImage(){
        LocalDate date = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = LocalDate.now();
        }
        String currentDate = String.valueOf(date);
        if(filePath!=null){
            FirebaseUser currentUser = mAuth.getCurrentUser();
            assert currentUser != null;
            String phoneNumber = currentUser.getPhoneNumber();
            //StorageReference ref=storageReference.child("Attendance images/"+phoneNumber);
            assert phoneNumber != null;
            StorageReference ref=storageReference.child("Attendance images").child(currentDate).child(phoneNumber);
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            assert phoneNumber != null;

                            dbRef.child("Date : " + currentDate).child(phoneNumber).child("photo").setValue(uri.toString());
                        //    NotifyUser("image uploaded successfully");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            NotifyUser(e.toString());
                        }
                    });
                }
            });
        }
    }

    private void NotifyUser(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            filePath=getImageUri(getApplicationContext(),photo);
            CheckIn();
            UploadImage();

        }
    }

    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String path=MediaStore.Images.Media.insertImage(getContentResolver(),photo,"title",null);
        return Uri.parse(path);
    }
}







