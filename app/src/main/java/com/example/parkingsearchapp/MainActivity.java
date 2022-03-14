package com.example.parkingsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public static String username;
    public static boolean flag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();
        checkMyPermission();
    }

    private void checkMyPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }

    public boolean loginFunc(View view) {
        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "login ok", Toast.LENGTH_LONG).show();
                            FlagClass.setFlag(true);
                        } else {
                            Toast.makeText(MainActivity.this, "login Fail", Toast.LENGTH_LONG).show();
                            FlagClass.setFlag(false);
                        }
                    }
                });
        return FlagClass.isFlag();

    }

    public boolean RegFunc() {
        String email = ((EditText) findViewById(R.id.editEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editPassword)).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "reg ok", Toast.LENGTH_LONG).show();
                            FlagClass.setFlag(true);

                        } else {
                            Toast.makeText(MainActivity.this, "reg fail", Toast.LENGTH_LONG).show();
                            FlagClass.setFlag(false);
                        }

                    }
                });
        return FlagClass.isFlag();
    }


    public void addData() {
        String email = ((EditText) findViewById(R.id.editEmail)).getText().toString();
        String first_name = ((EditText) findViewById(R.id.editFirstName)).getText().toString();
        String last_name = ((EditText) findViewById(R.id.editLastName)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editPhone)).getText().toString();
        String user_name=((EditText) findViewById(R.id.editUserName)).getText().toString();

        Person person = new Person(first_name, last_name, email, phone,user_name);
        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(person.getUser_name());
        myRef.setValue(person);
    }

    public void getData() {
        String email = ((EditText) findViewById(R.id.editEmail)).getText().toString();
        String first_name = ((EditText) findViewById(R.id.editFirstName)).getText().toString();
        String last_name = ((EditText) findViewById(R.id.editLastName)).getText().toString();
        String phone = ((EditText) findViewById(R.id.editPhone)).getText().toString();
        String user_name=((EditText) findViewById(R.id.editUserName)).getText().toString();

        Person person = new Person(first_name, last_name, email, phone,user_name);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(person.getUser_name());
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person value = dataSnapshot.getValue(Person.class);
                Toast.makeText(MainActivity.this, value.getUser_name(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // No user is signed in
        } else {
            // User logged in

        }
    }

}