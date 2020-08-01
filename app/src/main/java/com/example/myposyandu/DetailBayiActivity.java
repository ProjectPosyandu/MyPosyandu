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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bayi);

        ifotoBayi = findViewById(R.id.ifotoBayi);
        tvNamaBayi = findViewById(R.id.tvNamaBayi);
        tvInfoBayi = findViewById(R.id.tvInfoBayi);

        getIncomingExtra();

    }

    private void getIncomingExtra(){
//        String sfotoBayi = getIntent().getStringExtra("foto_bayi");
//        String snamaBayi = getIntent().getStringExtra("nama_bayi");
//        String sinfoBayi = getIntent().getStringExtra("info_bayi");
        String namaBayi = getIntent().getStringExtra("nama_bayi");
        String jenis = getIntent().getStringExtra("jenis_kelamin");

        setDataActivity(namaBayi, jenis);

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

    private void setDataActivity(String snamaBayi, String jenis){
//        Glide.with(this).asBitmap().load(sfotoBayi).into(ifotoBayi);
        tvNamaBayi.setText(snamaBayi);
        tvInfoBayi.setText(jenis);
    }
}
