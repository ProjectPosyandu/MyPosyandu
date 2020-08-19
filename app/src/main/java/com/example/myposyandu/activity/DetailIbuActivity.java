package com.example.myposyandu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myposyandu.R;
import com.example.myposyandu.SharedPrefManager;

public class DetailIbuActivity extends AppCompatActivity {
    TextView tvNama, tvUsername, tvTelp;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ibu);

        tvNama = findViewById(R.id.tvNama);
        tvUsername = findViewById(R.id.tvUsername);
        tvTelp = findViewById(R.id.tvTelp);
        sharedPrefManager = new SharedPrefManager(this);

        getDetailIbu();
    }

    private void getDetailIbu(){
        String snama = getIntent().getStringExtra("nama");
        String suser = getIntent().getStringExtra("username");
        String stelp = getIntent().getStringExtra("telp");
        setData(snama, suser, stelp);
    }

    private void setData(String snama, String suser, String stelp) {
        tvNama.setText(snama);
        tvUsername.setText(suser);
        tvTelp.setText(stelp);
    }
}
