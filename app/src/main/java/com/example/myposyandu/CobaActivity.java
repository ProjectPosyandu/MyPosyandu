package com.example.myposyandu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CobaActivity extends AppCompatActivity {
    private ArrayList<String> fotoBayi = new ArrayList<>();
    private ArrayList<String> namaBayi = new ArrayList<>();
    private ArrayList<String> detailBayi = new ArrayList<>();
    public FragmentManager f_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba);

        getDataFromInternet();
    }

    private void prosesRecyclerViewAdapter(){
        RecyclerView recyclerView = findViewById(R.id.rvDataBayi);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(fotoBayi,namaBayi, detailBayi, this, f_manager);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFromInternet(){
        namaBayi.add("Bayi Satu");
        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748829-bayi-dikelilingi-bunga-gaba-.jpg");
        detailBayi.add("Ini detail bayi");

        namaBayi.add("Bayi Dua");
        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748832-bayi-dikelilingi-bunga-gaba-.jpg");
        detailBayi.add("Ini detail bayi");

        namaBayi.add("Bayi Tiga");
        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748833-bayi-dikelilingi-bunga-gaba-.jpg");
        detailBayi.add("Ini detail bayi");

        prosesRecyclerViewAdapter();
    }
}
