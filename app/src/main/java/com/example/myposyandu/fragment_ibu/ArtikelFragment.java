package com.example.myposyandu.fragment_ibu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myposyandu.R;

import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.RetrofitClient;
import com.example.myposyandu.helper.UtilsApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ArtikelFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_artikel, container, false);

        return root;
    }



}
