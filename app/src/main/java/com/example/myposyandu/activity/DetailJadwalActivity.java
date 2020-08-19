package com.example.myposyandu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myposyandu.R;
import com.example.myposyandu.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;

public class DetailJadwalActivity extends AppCompatActivity {
    TextView tvJudul, tvTgl, tvJam;
    Button kirimPesan, setSelesai;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);

        tvJudul = findViewById(R.id.djNamaImunisasi);
        tvTgl = findViewById(R.id.djTglImunisasi);
        tvJam = findViewById(R.id.djJamImunisasi);
        kirimPesan = findViewById(R.id.djKirimPesan);
        setSelesai = findViewById(R.id.djSetSelesai);
        sharedPrefManager = new SharedPrefManager(this);

        getDetailArtikel();
    }

    private void getDetailArtikel(){
        String judul = getIntent().getStringExtra("nama_imunisasi");
        String tanggal = getIntent().getStringExtra("tanggal");
        String jam = getIntent().getStringExtra("waktu");
        setData(judul, tanggal, jam);

        String id_jadwal = getIntent().getStringExtra("id_jadwal");
        setSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSelesai(id_jadwal);
            }
        });
    }

    private void requestSelesai(String id_jadwal) {

    }

    private void setData(String judul, String tanggal, String jam) {
        tvJudul.setText(judul);
        tvTgl.setText(tanggal);
        tvJam.setText(jam);
    }

}
