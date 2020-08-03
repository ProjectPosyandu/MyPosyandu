package com.example.myposyandu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailBayiActivity extends AppCompatActivity {
    ImageView ifotoBayi;
    TextView tvNamaBayi, tvInfoBayi;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bayi);

        sharedPrefManager = new SharedPrefManager(this);

        ifotoBayi = findViewById(R.id.ifotoBayi);
        tvNamaBayi = findViewById(R.id.tvNamaBayi);
        tvInfoBayi = findViewById(R.id.tvInfoBayi);

        getIncomingExtra();

    }

    private void getIncomingExtra(){
//        String sfotoBayi = getIntent().getStringExtra("foto_bayi");
//        String snamaBayi = getIntent().getStringExtra("nama_bayi");
//        String sinfoBayi = getIntent().getStringExtra("info_bayi");
        String fotoBayi = getIntent().getStringExtra("foto_bayi");
        String namaBayi = getIntent().getStringExtra("nama_bayi");
        String jenis = getIntent().getStringExtra("jenis_kelamin");
        sharedPrefManager.saveSPString(sharedPrefManager.SP_JK, jenis);
        setDataActivity(fotoBayi, namaBayi, jenis);

//        if(getIntent().hasExtra("foto_bayi") && getIntent().hasExtra("nama_bayi") && getIntent().hasExtra("info_bayi")){
//            String sfotoBayi = getIntent().getStringExtra("foto_bayi");
//            String snamaBayi = getIntent().getStringExtra("nama_bayi");
//            String sinfoBayi = getIntent().getStringExtra("info_bayi");
//
//            setDataActivity(sfotoBayi, snamaBayi, sinfoBayi);
//        }else{
//            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
//        }
    }

    private void setDataActivity(String fotoBayi, String snamaBayi, String jenis){
        Glide.with(this).asBitmap().load(fotoBayi).into(ifotoBayi);
        tvNamaBayi.setText(snamaBayi);
        tvInfoBayi.setText(jenis);
    }
}
