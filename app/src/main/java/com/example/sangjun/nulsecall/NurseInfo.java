package com.example.sangjun.nulsecall;

/**
 * Created by SangJun on 2015-11-30.
 */
public class NurseInfo {
    private String[] mData;

    public NurseInfo(String[] data){
        mData = data;
    }

    public NurseInfo(String id, String pass, String name, String role){
        mData = new String[4];
        mData[0] = id;
        mData[1] = pass;
        mData[2] = name;
        mData[3] = role;

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
