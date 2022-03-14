package com.example.parkingsearchapp;

public abstract class FlagClass {
    public static boolean flag;

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag_in) {
        flag = flag_in;
    }
}
