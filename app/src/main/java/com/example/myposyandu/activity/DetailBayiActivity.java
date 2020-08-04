package com.example.myposyandu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myposyandu.R;
import com.example.myposyandu.SharedPrefManager;
import com.example.myposyandu.fragment_ibu.ChartFragment;

public class DetailBayiActivity extends AppCompatActivity {
    ImageView ifotoBayi;
    TextView tvNamaBayi, tvJK, tvTgl;
    Button btnChart, btnUpdate;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bayi);

        sharedPrefManager = new SharedPrefManager(this);

        ifotoBayi = findViewById(R.id.ifotoBayi);
        tvNamaBayi = findViewById(R.id.tvNamaBayi);
        tvJK = findViewById(R.id.tvJenisKelamin);
        tvTgl = findViewById(R.id.tvTglLahir);
        btnChart = findViewById(R.id.btnChart);
        btnUpdate = findViewById(R.id.btnUpdateChart);

        getIncomingExtra();

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new ChartFragment()).commit();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new ChartFragment()).commit();
            }
        });

    }

    private void getIncomingExtra(){
//        String fotoBayi = getIntent().getStringExtra("foto_bayi");
        String namaBayi = getIntent().getStringExtra("nama_bayi");
        String jenis = getIntent().getStringExtra("jenis_kelamin");
        String tglLahir = getIntent().getStringExtra("tgl_lahir");
        sharedPrefManager.saveSPString(sharedPrefManager.SP_JK, jenis);
        setDataActivity(namaBayi, jenis, tglLahir);

    }

    private void setDataActivity(String snamaBayi, String jenis, String tglLahir){
//        Glide.with(this).asBitmap().load(fotoBayi).into(ifotoBayi);
        tvNamaBayi.setText(snamaBayi);
        tvJK.setText(jenis);
        tvTgl.setText(tglLahir);
    }


}
