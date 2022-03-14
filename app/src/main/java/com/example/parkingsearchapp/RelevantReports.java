package com.example.parkingsearchapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RelevantReports#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelevantReports extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private RecyclerView reports_list;
   // private DatabaseReference UsersRef, ReportsRef;
    List<Reports> reportsData;
    RecyclerView recyclerView;
    HelperAdapter helperAdapter;
    DatabaseReference databaseReference,LikesRef;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RelevantReports() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RelevantReports.
     */
    // TODO: Rename and change types and number of parameters
    public static RelevantReports newInstance(String param1, String param2) {
        RelevantReports fragment = new RelevantReports();
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
        View view = inflater.inflate(R.layout.fragment_relevant_reports, container, false);


        ParkingSearch activity_srch = new ParkingSearch();
        String FilterIsCost = activity_srch.sendOption1();
        String FilterIsIndoor = activity_srch.sendOption2();
        String FilterIsDisabled = activity_srch.sendOption3();


        recyclerView = view.findViewById(R.id.MyReportsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reportsData=new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ParkingSearch parkingSearch = (ParkingSearch) getActivity();
        String userName = parkingSearch.sendUserName();
        String curr_latit = parkingSearch.transferLat();
        String curr_longit = parkingSearch.transferLon();
        double curr_latitude = Double.parseDouble(curr_latit);
        double curr_longitude = Double.parseDouble(curr_longit);
        Button like_btn = view.findViewById(R.id.imageLike);
        LikesRef = FirebaseDatabase.getInstance().getReference().child("likes");
        DatabaseReference statusRef = databaseReference.child("reports");
        statusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren())
                {
                    if(ds.exists()) {
                        String IsCost = ds.child("isCost").getValue().toString();
                        String IsIndoor = ds.child("isIndoor").getValue().toString();
                        String IsDisabled = ds.child("isDisabled").getValue().toString();
                        String dest_lat = ds.child("latitude").getValue().toString();
                        String dest_lon=ds.child("longitude").getValue().toString();

                        double destination_latitude= Double.parseDouble(dest_lat);
                        double destination_longitude = Double.parseDouble(dest_lon);
                        double distance = calculate_distance(curr_latitude,curr_longitude,destination_latitude,destination_longitude);

                        if (IsCost.equals(FilterIsCost) && IsIndoor.equals(FilterIsIndoor) && IsDisabled.equals(FilterIsDisabled) && distance<1000 ) {
                            Reports data = ds.getValue(Reports.class);
                            reportsData.add(data);

                        }
                    }

                }
                helperAdapter = new HelperAdapter(reportsData);
                recyclerView.setAdapter(helperAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    public static double calculate_distance(double curr_latitude,double curr_longitude,double dest_lat,double dest_lon){
        curr_latitude = Math.toRadians(curr_latitude);
        curr_longitude= Math.toRadians(curr_longitude);
        dest_lat = Math.toRadians(dest_lat);
        dest_lon = Math.toRadians(dest_lon);
        double dlon =dest_lon - curr_longitude;
        double dlat = dest_lat - curr_latitude;
        double a= Math.pow(Math.sin(dlat/2),2)
                + Math.cos(curr_latitude)* Math.cos(dest_lat)
                * Math.pow(Math.sin(dlon/2),2);
        double c= 2* Math.asin(Math.sqrt(a));
        double r = 6371000;
        return (c*r);
    }


}