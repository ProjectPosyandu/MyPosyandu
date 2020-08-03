package com.example.myposyandu.fragment_ibu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myposyandu.R;

public class DetailBayiFragment extends Fragment {
    ImageView ifotoBayi;
    TextView tvNamaBayi, tvInfoBayi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_bayi, container, false);
        ifotoBayi = view.findViewById(R.id.ifotoBayi);
        tvNamaBayi = view.findViewById(R.id.tvNamaBayi);
        tvInfoBayi = view.findViewById(R.id.tvInfoBayi);

//        getIncomingExtra();

        return view;
    }

//    private void getIncomingExtra(){
//        String sfotoBayi = getIntent().getStringExtra("foto_bayi");
//        String snamaBayi = getIntent().getStringExtra("nama_bayi");
//        String sinfoBayi = getIntent().getStringExtra("info_bayi");
//
//        setDataActivity(sfotoBayi, snamaBayi, sinfoBayi);
//
////        if(getIntent().hasExtra("foto_bayi") && getIntent().hasExtra("nama_bayi") && getIntent().hasExtra("info_bayi")){
////            String sfotoBayi = getIntent().getStringExtra("foto_bayi");
////            String snamaBayi = getIntent().getStringExtra("nama_bayi");
////            String sinfoBayi = getIntent().getStringExtra("info_bayi");
////
////            setDataActivity(sfotoBayi, snamaBayi, sinfoBayi);
////        }else{
////            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
////        }
//    }
//
//    private void setDataActivity(String sfotoBayi, String snamaBayi, String sinfoBayi){
//        Glide.with(this).asBitmap().load(sfotoBayi).into(ifotoBayi);
//        tvNamaBayi.setText(snamaBayi);
//        tvInfoBayi.setText(sinfoBayi);
//    }
}
