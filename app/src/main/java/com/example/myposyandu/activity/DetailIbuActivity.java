package com.example.myposyandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myposyandu.R;
import com.example.myposyandu.SharedPrefManager;
import com.example.myposyandu.adapter.RecyclerViewAdapter;
import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.UtilsApi;
import com.example.myposyandu.model.ModelDataBayi;
import com.example.myposyandu.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailIbuActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<ModelDataBayi> listData = new ArrayList<>();
    TextView status;

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


        rvData = findViewById(R.id.rvDataBayi);
        status = findViewById(R.id.status);
        lmData = new LinearLayoutManager(DetailIbuActivity.this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        rvData.addItemDecoration(new DividerItemDecoration(DetailIbuActivity.this, DividerItemDecoration.VERTICAL));
        sharedPrefManager = new SharedPrefManager(DetailIbuActivity.this);
        String id = sharedPrefManager.getSpId();


//        getDetailIbu();
//        String id = getIntent().getStringExtra("id");
        String snama = getIntent().getStringExtra("nama");
        String suser = getIntent().getStringExtra("username");
        String stelp = getIntent().getStringExtra("telp");
        getDataBayi(id);
        setData(snama, suser, stelp);

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

    public void getDataBayi(String id){
        ApiService ardData = UtilsApi.getAPIService();
        Call<ResponseModel> tampilData = ardData.getDataBayi(id);
        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                if(pesan.equals("Data tersedia")){
                    status.setVisibility(View.INVISIBLE);
                    rvData.setVisibility(View.VISIBLE);

                    listData = response.body().getData();
                    adData = new RecyclerViewAdapter(getApplication(), listData);
                    rvData.setAdapter(adData);
                    adData.notifyDataSetChanged();

                }else {
                    status.setVisibility(View.VISIBLE);
                    rvData.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DetailIbuActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
