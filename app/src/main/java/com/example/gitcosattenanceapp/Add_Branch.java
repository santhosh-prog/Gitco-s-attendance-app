package com.example.gitcosattenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Add_Branch extends AppCompatActivity {
    EditText branchName,branchLocation;
    Button addBranch;
    String sBranchName,sBranchLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_branch);
        branchName=findViewById(R.id.addBranchName);
        branchLocation=findViewById(R.id.addBranchLocation);

        initViews();

    }

    private void initViews() {

        sBranchName=branchName.getText().toString();
      //  branchLocation.setOnClickListener();

    }
}