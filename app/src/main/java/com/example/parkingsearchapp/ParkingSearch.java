package com.example.parkingsearchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ParkingSearch extends AppCompatActivity {
    public static String option1="לא",option2="לא",option3="לא";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_search);
        Button find = findViewById(R.id.buttonFind);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        CheckBox IsCostBox = (CheckBox) findViewById(R.id.checkBoxIsCost);
        CheckBox IsDisabledBox =(CheckBox) findViewById(R.id.checkBoxIsDisabled);
        CheckBox IsIndoorBox = (CheckBox) findViewById(R.id.checkBoxIsIndoor);
        Bundle bundle = getIntent().getExtras();
        String address = bundle.getString("Address");
        TextView  addressText = findViewById(R.id.PlaceSearch);
        TextView  titleTextFilter =findViewById(R.id.titleFilter);
        TextView titleSearchText = findViewById(R.id.TitleSearch);
        CardView place_card = findViewById(R.id.CardPlace);
        CardView filter_card =findViewById(R.id.CardFilter);
        addressText.setText(address);


        IsCostBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    option1="כן";
                }
                else{
                    option1="לא";

                }
            }
        });
        IsIndoorBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    option2="כן";
                }
                else{
                    option2="לא";

                }
            }
        });
        IsDisabledBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    option3="כן";
                }
                else{
                    option3="לא";

                }
            }
        });
        Button back_btn = findViewById(R.id.buttonBack);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find.setVisibility(View.GONE);
                IsCostBox.setVisibility(View.GONE);
                IsIndoorBox.setVisibility(View.GONE);
                IsDisabledBox.setVisibility(View.GONE);
                addressText.setVisibility(View.GONE);
                titleSearchText.setVisibility(View.GONE);
                titleTextFilter.setVisibility(View.GONE);
                place_card.setVisibility(View.GONE);
                filter_card.setVisibility(View.GONE);
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("RP");
                if(fragment != null)
                {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                getSupportFragmentManager().beginTransaction().add(R.id.container_fragment,new MainScreen()).commit();

            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("RP");
                if(fragment != null)
                {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.container,new RelevantReports(),"RP").commit();
                }
                else {
                    getSupportFragmentManager().beginTransaction().add(R.id.container, new RelevantReports(), "RP").commit();
                }
            }
        });
    }
    public String sendOption1() {
        return option1;
    }
    public String sendOption2() {
        return option2;
    }
    public String sendOption3() {
        return option3;
    }

    public String sendUserName(){
        Intent i=getIntent();
        String username= i.getStringExtra("user_name");

        return username;
    }
    public String transferLat(){
        Intent i_lat=getIntent();
        String current_latitude = i_lat.getStringExtra("Latitude");

        return current_latitude;
    }

    public String transferLon(){
        Intent i_lon = getIntent();
        String current_longitude= i_lon.getStringExtra("Longitude");

        return current_longitude;
    }

}