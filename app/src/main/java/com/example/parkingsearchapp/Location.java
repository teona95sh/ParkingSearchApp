package com.example.parkingsearchapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable{
    private String Latitude;
    private String Longitude;

    public Location(String x, String y)
    {
        Latitude = x;
        Longitude = y;
    }

    protected Location(Parcel in) {
        Latitude = in.readString();
        Longitude = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getLatitude(){return Latitude;}

    public String getLongitude(){return Longitude;}

    public void setLatitude(String x){ Latitude=x;}
    public void setLongitude(String y){Longitude=y;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Latitude);
        parcel.writeString(Longitude);
    }
}
