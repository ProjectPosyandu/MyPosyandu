package com.example.myposyandu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myposyandu.R;

public class JadwalFragment extends Fragment {

     public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jadwal, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        textView.setText("Disini jadwal");
        return root;
    }
}
