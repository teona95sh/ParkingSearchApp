package com.example.parkingsearchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PopUpSettings extends Activity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private String current_user_email;
    private static String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_settings);
        DisplayMetrics dp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dp);
        int width = dp.widthPixels;
        int height = dp.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .7));
        TextView curr_user_name= findViewById(R.id.textUserName);
        Intent i=getIntent();
        String username= i.getStringExtra("user_name");
        curr_user_name.setText(username);
        ImageButton ib = (ImageButton)findViewById(R.id.imageX);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btn_watch_profile = findViewById(R.id.buttonWatchProfile);
        btn_watch_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_profile_screen = new Intent(PopUpSettings.this,MyProfile.class);
                my_profile_screen.putExtra("user_name",username);
                startActivity(my_profile_screen);
            }
        });
        Button btn_watch_reports = findViewById(R.id.buttonWatchReports);
        btn_watch_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_reports_screen = new Intent(PopUpSettings.this,MyReports.class);
                my_reports_screen.putExtra("user_name",username);

                startActivity(my_reports_screen);
            }
        });


    }
}