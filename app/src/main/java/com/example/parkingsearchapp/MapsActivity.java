package com.example.parkingsearchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.parkingsearchapp.databinding.ActivityMapsBinding;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    SearchView searchView;
    Marker marker;
    double lat1 = 0;
    double lot1 = 0;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView = findViewById(R.id.idSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    Address address = addressList.get(0);

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    // on below line we are adding marker to that position.
                    // Marker marker_new = mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    if (marker == null) {
                        marker = mMap.addMarker(new MarkerOptions());
                    } else {
                        marker.setPosition(new LatLng(address.getLatitude(), address.getLongitude()));
                    }

                    // below line is to animate camera to that position.
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        Button confirm_btn = findViewById(R.id.buttonConfirm);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = getIntent().getExtras();
                if (bundle2 != null) {
                    lat1 = bundle2.getDouble("current_lat");
                    lot1 = bundle2.getDouble("current_lot");
                }
                LatLng pos =marker.getPosition();
                String latitude_str = String.valueOf(pos.latitude);
                String longitude_str = String.valueOf(pos.longitude);
                Locale locale = new Locale("he","IL");
                Locale.setDefault(locale);
                Geocoder geocoder;
                List<Address> addresses;

                //Intent i_report = new Intent(MapsActivity.this, ParkingReport.class);
                Intent my_intent;
                my_intent= switch_activities();
                geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(pos.latitude, pos.longitude, 1);
                    String address = addresses.get(0).getAddressLine(0);
                    String city = addresses.get(0).getLocality();
                    String knownName = addresses.get(0).getFeatureName();
                    my_intent.putExtra("Address",address);
                    my_intent.putExtra("City",city);
                    my_intent.putExtra("Known Name",knownName);
                } catch (IOException e) {
                    e.printStackTrace();
                    }
                my_intent.putExtra("Latitude",latitude_str);
                my_intent.putExtra("Longitude",longitude_str);
                my_intent.putExtra("current_lat",String.valueOf(lat1));
                my_intent.putExtra("current_lot",String.valueOf(lot1));
                startActivity(my_intent);
            }
        });
        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 30);
    }

    public Intent switch_activities(){
        Intent i22=getIntent();
        String btn_type= i22.getStringExtra("Button");
        String user_name = i22.getStringExtra("user_name");
        Intent my_intent;
        if(btn_type.equals("btn_search")){
             my_intent = new Intent(MapsActivity.this, ParkingSearch.class);
             my_intent.putExtra("user_name",user_name);


        }
        else {
            my_intent = new Intent(MapsActivity.this, ParkingReport.class);
            my_intent.putExtra("user_name",user_name);

        }
        return my_intent;
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude+ " : " + latLng.longitude);
                //mMap.clear();
                //for zoom in map
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLng,16
                ));
                //adding red marker
                if (marker == null) {
                    marker = mMap.addMarker(markerOptions);
                } else {
                    marker.setPosition(latLng);
                }
                //mMap.addMarker(markerOptions);
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            lat1 = bundle.getDouble("current_lat");
            lot1 = bundle.getDouble("current_lot");
        }
        LatLng israel = new LatLng(lat1, lot1);
        marker = mMap.addMarker(new MarkerOptions()
                .position(israel)
                .title("").draggable(true));

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(israel));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(israel, 16));
    }


}