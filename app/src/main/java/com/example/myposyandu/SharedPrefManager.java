package com.example.myposyandu;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_POSYANDU_APP = "spPosyanduApp";

    public static final String SP_ID = "spId";
    public int SP_ID_BAYI = 0;
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_JK = "spJk";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_POSYANDU_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(int keySP, int value){
        spEditor.putInt(String.valueOf(keySP), value);
        spEditor.commit();
    }

    public String getSpId(){ return sp.getString(SP_ID, ""); }

    public int getSpIdBayi(){ return sp.getInt(String.valueOf(SP_ID_BAYI), 0); }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpJk(){
        return  sp.getString(SP_JK, "");
    }
}
