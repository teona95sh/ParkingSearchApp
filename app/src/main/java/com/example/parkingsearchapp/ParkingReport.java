package com.example.parkingsearchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingReport extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,ref1,ref2;
    private String savecurr_date,savecurr_time,report_date;
    private long count_reports=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_report);
        TextView place_text = findViewById(R.id.PlaceSearch);
        Bundle bundle = getIntent().getExtras();
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String address = bundle.getString("Address");
        place_text.setText(address);
        /*Spinner dropdownIsCost = findViewById(R.id.spinnerIsCost);
        //String[] items = new String[]{"כן", "לא", "לא ידוע"};
        List<String> filters = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        categories.add(0, "");
        categories.add("כן");
        categories.add("לא");
        Map<String, String> dictionary = new HashMap<String, String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownIsCost.setAdapter(adapter);
        Intent in = getIntent();
        String user_name = in.getStringExtra("user_name");

        Calendar cal_val =Calendar.getInstance();
        SimpleDateFormat curr_date = new SimpleDateFormat("dd-MM-yyyy");
        savecurr_date = curr_date.format(cal_val.getTime());

        Calendar cal_time = Calendar.getInstance();
        SimpleDateFormat curr_time = new SimpleDateFormat("HH:mm");
        savecurr_time = curr_time.format(cal_time.getTime());

        report_date = user_name + ","+ savecurr_date + "," + savecurr_time;
        EditText ParkingAmount = findViewById(R.id.editTextFree);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("reports").child(report_date);
        databaseReference.child("address").setValue(address);
        databaseReference.child("date").setValue(savecurr_date);
        String amnt_parking =((EditText) findViewById(R.id.editTextFree)).getText().toString();
        databaseReference.child("free_Parking_Amount").setValue(amnt_parking);
        databaseReference.child("time").setValue(savecurr_time);
        databaseReference.child("post_id").setValue(report_date);
        databaseReference.child("latitude").setValue(lat);
        databaseReference.child("longitude").setValue(lot);
        databaseReference.child("user_name").setValue(user_name);

        dropdownIsCost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
                if (parent.getItemAtPosition(position).equals("כן")) {
                    dictionary.put("isCost", "כן");
                   // Query query = FirebaseDatabase.getInstance().getReference("users").orderByChild(user_name).;
                    databaseReference.child("isCost").setValue("כן");

                }
                if (parent.getItemAtPosition(position).equals("לא")) {
                    dictionary.put("isCost", "לא");
                    databaseReference.child("isCost").setValue( "לא");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        Spinner dropdownIsIndoor = findViewById(R.id.spinnerIsIndoor);
        dropdownIsIndoor.setAdapter(adapter);
        dropdownIsIndoor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
                if (parent.getItemAtPosition(position).equals("כן")) {
                    dictionary.put("isIndoor", "כן");
                    databaseReference.child("isIndoor").setValue( "כן");

                }
                if (parent.getItemAtPosition(position).equals("לא")) {
                    dictionary.put("isIndoor", "לא");
                    databaseReference.child("isIndoor").setValue( "לא");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        Spinner dropdownIsDisabled = findViewById(R.id.spinnerIsDisabled);
        TextView textAmountDis = findViewById(R.id.textAmountDis);
        EditText TextAmount =  findViewById(R.id.editTextAmount);
        dropdownIsDisabled.setAdapter(adapter);
        dropdownIsDisabled.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
                if (parent.getItemAtPosition(position).equals("כן")) {
                    dictionary.put("isDisabled", "כן");
                    databaseReference.child("isDisabled").setValue("כן");

                    textAmountDis.setVisibility(View.VISIBLE);
                    TextAmount.setVisibility(View.VISIBLE);

                }
                else {
                    textAmountDis.setVisibility(View.INVISIBLE);
                    TextAmount.setVisibility(View.INVISIBLE);
                }
                if (parent.getItemAtPosition(position).equals("לא")) {
                    dictionary.put("isDisabled", "לא");
                    databaseReference.child("isDisabled").setValue("לא");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        addData();

        Button rpt_btn = findViewById(R.id.buttonReport);
        rpt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner dropdownIsCost = findViewById(R.id.spinnerIsCost);
                Spinner dropdownIsIndoor = findViewById(R.id.spinnerIsIndoor);
                Spinner dropdownIsDisabled = findViewById(R.id.spinnerIsDisabled);
                EditText ParkingAmount = findViewById(R.id.editTextFree);
                EditText TextAmount =  findViewById(R.id.editTextAmount);

                if(ParkingAmount.getText().toString().isEmpty() || dropdownIsDisabled.getSelectedItem().toString().isEmpty() || dropdownIsCost.getSelectedItem().toString().isEmpty()
                        || dropdownIsIndoor.getSelectedItem().toString().isEmpty() || (TextAmount.getText().toString().isEmpty() && TextAmount.getVisibility()==View.VISIBLE)){
                        if(ParkingAmount.getText().toString().isEmpty()){
                            ParkingAmount.setError("שדה זה הוא שדה חובה!");
                        }
                        if(TextAmount.getText().toString().isEmpty()){
                            TextAmount.setError("שדה זה הוא שדה חובה!");

                        }
                        if(dropdownIsDisabled.getSelectedItem().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"שדה 'האם קיימות חניות נכה' הוא שדה חובה!",
                                    Toast.LENGTH_LONG).show();
                        }
                        if(dropdownIsCost.getSelectedItem().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "שדה 'האם החניה בתשלום' הוא שדה חובה!",
                                    Toast.LENGTH_LONG).show();
                        }
                        if(dropdownIsIndoor.getSelectedItem().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(),"שדה 'האם החניה מקורה' הוא שדה חובה!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Intent intent_main_finish = new Intent(ParkingReport.this,AfterReport.class);
                        startActivity(intent_main_finish);
                    }

            }
        });
    }
    public void addData(){
        Intent in = getIntent();
        Bundle bundle = getIntent().getExtras();

        String lat = bundle.getString("Latitude");
        String lot = bundle.getString("Longitude");
        String address = bundle.getString("Address");
        String user_name = in.getStringExtra("user_name");
        Spinner dropdownIsCost = findViewById(R.id.spinnerIsCost);
        Spinner dropdownIsIndoor = findViewById(R.id.spinnerIsIndoor);
        Spinner dropdownIsDisabled = findViewById(R.id.spinnerIsDisabled);
        TextView textAmountDis = findViewById(R.id.textAmountDis);
        EditText TextAmount =  findViewById(R.id.editTextAmount);


        Calendar cal_val =Calendar.getInstance();
        SimpleDateFormat curr_date = new SimpleDateFormat("dd-MM-yyyy");
        savecurr_date = curr_date.format(cal_val.getTime());

        Calendar cal_time = Calendar.getInstance();
        SimpleDateFormat curr_time = new SimpleDateFormat("HH:mm");
        savecurr_time = curr_time.format(cal_time.getTime());

        report_date = user_name + ","+ savecurr_date + "," + savecurr_time;
        EditText ParkingAmount = findViewById(R.id.editTextFree);
        List<String> categories = new ArrayList<>();
        categories.add(0, "");
        categories.add("כן");
        categories.add("לא");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("reports").child(report_date);
        databaseReference.child("address").setValue(address);
        databaseReference.child("date").setValue(savecurr_date);
        String amnt_parking =((EditText) findViewById(R.id.editTextFree)).getText().toString();
        databaseReference.child("free_Parking_Amount").setValue(amnt_parking);
        databaseReference.child("time").setValue(savecurr_time);
        databaseReference.child("post_id").setValue(report_date);
        databaseReference.child("latitude").setValue(lat);
        databaseReference.child("longitude").setValue(lot);
        databaseReference.child("user_name").setValue(user_name);

        dropdownIsCost.setAdapter(adapter);

        dropdownIsCost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
                if (parent.getItemAtPosition(position).equals("כן")) {
                    // Query query = FirebaseDatabase.getInstance().getReference("users").orderByChild(user_name).;
                    databaseReference.child("isCost").setValue("כן");

                }
                if (parent.getItemAtPosition(position).equals("לא")) {
                    databaseReference.child("isCost").setValue( "לא");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        dropdownIsIndoor.setAdapter(adapter);
        dropdownIsIndoor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
                if (parent.getItemAtPosition(position).equals("כן")) {
                    databaseReference.child("isIndoor").setValue( "כן");

                }
                if (parent.getItemAtPosition(position).equals("לא")) {
                    databaseReference.child("isIndoor").setValue( "לא");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        dropdownIsDisabled.setAdapter(adapter);
        dropdownIsDisabled.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
                if (parent.getItemAtPosition(position).equals("כן")) {
                    databaseReference.child("isDisabled").setValue("כן");

                    textAmountDis.setVisibility(View.VISIBLE);
                    TextAmount.setVisibility(View.VISIBLE);

                }
                else {
                    textAmountDis.setVisibility(View.INVISIBLE);
                    TextAmount.setVisibility(View.INVISIBLE);
                }
                if (parent.getItemAtPosition(position).equals("לא")) {
                    databaseReference.child("isDisabled").setValue("לא");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(TextAmount.getVisibility()== View.VISIBLE) {
            String IsDisabledAmount = TextAmount.getText().toString();
            databaseReference.child("isDisabledAmount").setValue(IsDisabledAmount);
        }
        else{
            databaseReference.child("isDisabledAmount").setValue("אין");

        }
    }




}