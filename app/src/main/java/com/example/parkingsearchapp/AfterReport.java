package com.example.parkingsearchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AfterReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_report);
        Button btn_back =  findViewById(R.id.buttonBack);
        TextView textView = findViewById(R.id.titleReportsSuccessed);
        Button back_btn = findViewById(R.id.buttonBack);
        ImageView car_img = findViewById(R.id.imageCar);
        ImageView smile_img = findViewById(R.id.imageSmiley);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
                back_btn.setVisibility(View.GONE);
                car_img.setVisibility(View.GONE);
                smile_img.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new MainScreen()).commit();
            }
        });
    }
}