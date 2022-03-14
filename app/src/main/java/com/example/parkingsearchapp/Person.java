package com.example.parkingsearchapp;

public class Person {

    String first_name;
    String last_name;
    String mail;
    String phone;
    String user_name;

    public Person(String first_name,String last_name, String mail,String phone,String user_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.phone=phone;
        this.user_name=user_name;
    }
    public Person(){

    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }


}
