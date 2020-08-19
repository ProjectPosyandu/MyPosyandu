package com.example.myposyandu.fragment_kader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myposyandu.R;

import cyd.awesome.material.AwesomeText;

public class TambahIbuFragment extends Fragment {
    AwesomeText ImgShowHidePassword;
    boolean pwd_status = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tambah_ibu, container, false);
    }
}
