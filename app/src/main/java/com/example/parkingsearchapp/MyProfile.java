package com.example.parkingsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyProfile extends AppCompatActivity {
    private DatabaseReference databaseReference;
    List<Person> profileData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("user_name");
        TextView emailText = findViewById(R.id.emailText);
        TextView first_name_text = findViewById(R.id.firstNameText);
        TextView last_name_text = findViewById(R.id.lastNameText);
        TextView phone_text = findViewById(R.id.phoneText);
        TextView user_name = findViewById(R.id.userNameText);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(username);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Person data = snapshot.getValue(Person.class);
                    emailText.setText(data.getMail());
                    first_name_text.setText(data.getFirst_name());
                    last_name_text.setText(data.getLast_name());
                    phone_text.setText(data.getPhone());
                    user_name.setText(data.getUser_name());
                    profileData.add(data);

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}