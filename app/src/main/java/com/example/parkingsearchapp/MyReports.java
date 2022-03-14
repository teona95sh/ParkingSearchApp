package com.example.parkingsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyReports extends AppCompatActivity {
    private DatabaseReference databaseReference;
    List<Reports> reportsUserData;
    RecyclerView recyclerView;
    MyReportsHelperAdapter helperAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("user_name");
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView = findViewById(R.id.MyReportsUserRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyReports.this));
        reportsUserData=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("reports");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    if(ds.exists()) {
                        if (ds.getValue().toString().contains(username)) {
                            Reports data = ds.getValue(Reports.class);
                            reportsUserData.add(data);

                        }
                    }
                }
                helperAdapter = new MyReportsHelperAdapter(reportsUserData);
                recyclerView.setAdapter(helperAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}