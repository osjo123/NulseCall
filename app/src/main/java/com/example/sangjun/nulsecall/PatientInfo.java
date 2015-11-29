package com.example.sangjun.nulsecall;

/**
 * Created by SangJun on 2015-11-30.
 */
public class PatientInfo {
    private String[] mData;

    public PatientInfo(String[] data){
        mData = data;
    }

    public PatientInfo(String id, String pass, String name, String dx, String nurse, String role){
        mData = new String[6];
        mData[0] = id;
        mData[1] = pass;
        mData[2] = name;
        mData[3] = dx;
        mData[4] = nurse;
        mData[5] = role;
    }

    public String[] getData(){
        return mData;
    }
    public String getData(int index){
        return mData[index];
    }
    public void setData(String[] data){
        mData = data;
    }
}
