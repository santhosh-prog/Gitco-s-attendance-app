package com.example.gitcosattenanceapp.admin_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gitcosattenanceapp.Add_employee;
import com.example.gitcosattenanceapp.Check_attendance;
import com.example.gitcosattenanceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin_Home_fragment extends Fragment{
    Button select_branch_button,check_attendance,add_employee;

    String email;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin__home_fragment, container, false);
        check_attendance=view.findViewById(R.id.check_attendance_button);
        select_branch_button=view.findViewById(R.id.select_branch_popup);
        add_employee=view.findViewById(R.id.add_employee_button);



        select_branch_button.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(getActivity(), select_branch_button);

            popupMenu.getMenuInflater().inflate(R.menu.branch_popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(getActivity(), "You Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });
            popupMenu.show();
        });

        check_attendance.setOnClickListener(v -> startActivity(new Intent(getActivity(), Check_attendance.class)));

        add_employee.setOnClickListener(v -> startActivity(new Intent(getActivity(), Add_employee.class)));

        return view;
    }

}