package com.example.parkingdost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.parkingdost.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.nullness.qual.NonNull;

public class signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = auth.getCurrentUser().getUid();
                DatabaseReference userRef = db.getReference("users").child(userId);
                Users user = new Users(binding.etCarModel.getText().toString(), binding.etDriverName.getText().toString(), binding.etLicensePlate.getText().toString(), binding.etPhoneNumber.getText().toString());
//                ref.child("USERS").child(userId).setValue("Car model.",binding.etCarModel.getText().toString());
//                ref.child("USERS").child(userId).setValue("Driver licence.",binding.etLicensePlate.getText().toString());
//                ref.child("USERS").child(userId).setValue("phone number:",binding.etPhoneNumber.getText().toString());
//                ref.child("USERS").child(userId).setValuebinding.etDriverName.getText().toString());
//


                Toast.makeText(signup.this, "signup successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);

            }


        });
    }
}