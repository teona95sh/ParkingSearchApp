package com.example.parkingsearchapp;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LocationManager locationManager;
    private String provider;
    private TextView latitudeField;
    private TextView longitudeField;
    private TextView LocationField;
    //Button btn_parking_report;
    //Button btn_parking_search;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,user_reports;
    private FirebaseUser firebaseUser;
    private String current_user_email;
    private static String user_name;

    public MainScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static MainScreen newInstance(String param1, String param2) {
        MainScreen fragment = new MainScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        current_user_email = firebaseAuth.getCurrentUser().getEmail();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data_snapshot : snapshot.getChildren()) {
                    String mail = data_snapshot.child("mail").getValue().toString();
                    if(current_user_email.equals(mail)){
                        user_name = data_snapshot.child("user_name").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main_screen, container, false);
        ImageButton imageButton = view.findViewById(R.id.userPictureButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),PopUpSettings.class);
                i.putExtra("user_name",user_name);
                startActivity(i);
            }
        });
        Button btn_report = view.findViewById(R.id.ButtonParkingReport);
        Button btn_search = view.findViewById(R.id.ButtonParkingSearch);
        //btn_report.setOnClickListener(this);
        //btn_search.setOnClickListener(this);
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main = new Intent(getContext(),LocationActivity.class);
                intent_main.putExtra("Button","btn_report");
                intent_main.putExtra("user_name",user_name);
                startActivity(intent_main);
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main2 = new Intent(getContext(),LocationActivity.class);
                intent_main2.putExtra("Button","btn_search");
                intent_main2.putExtra("user_name",user_name);

                startActivity(intent_main2);
            }
        });

        return view;
    }



}