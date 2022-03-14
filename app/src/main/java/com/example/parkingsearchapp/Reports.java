package com.example.parkingsearchapp;

public class Reports {
    public String address;
    public String date;
    public String free_Parking_Amount;
    public String isCost;
    public String isDisabled;
    public String isDisabledAmount;
    public String isIndoor;
    public String latitude;
    public String longitude;
    public String post_id;
    public String time;
    public String user_name;




    public Reports(){

    }


    public Reports(String address, String date, String free_Parking_Amount , String isCost, String isDisabled, String isDisabledAmount, String isIndoor, String latitude, String longitude, String post_id, String time, String user_name){
        this.address=address;
        this.date=date;
        this.free_Parking_Amount =free_Parking_Amount;
        this.isCost=isCost;
        this.isDisabled=isDisabled;
        this.isDisabledAmount=isDisabledAmount;
        this.isIndoor=isIndoor;
        this.latitude=latitude;
        this.longitude=longitude;
        this.post_id=post_id;
        this.time=time;
        this.user_name=user_name;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setFree_Parking_Amount(String freeParkingAmount) {
        free_Parking_Amount = freeParkingAmount;
    }

    public String getFree_Parking_Amount() {
        return free_Parking_Amount;
    }

    public void setIsCost(String is_Cost) {
        isCost = is_Cost;
    }

    public String getIsCost() {
        return isCost;
    }

    public void setIsDisabled(String is_Disabled) {
        isDisabled = is_Disabled;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabledAmount(String is_Disabled_Amount) {
        isDisabledAmount = is_Disabled_Amount;
    }

    public String getIsDisabledAmount() {
        return isDisabledAmount;
    }

    public void setIsIndoor(String is_Indoor) {
        isIndoor = is_Indoor;
    }

    public String getIsIndoor() {
        return isIndoor;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }




}
