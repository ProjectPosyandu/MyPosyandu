package com.example.myposyandu.fragment_ibu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myposyandu.R;
import com.example.myposyandu.RecyclerViewAdapter;
import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.UtilsApi;
import com.example.myposyandu.model.ModelDataBayi;
import com.example.myposyandu.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBayiFragment extends Fragment {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<ModelDataBayi> listData = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_data_bayi, container, false);
        rvData = root.findViewById(R.id.rvDataBayi);
        lmData = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        getDataBayi();

        return root;
    }

//    private void prosesRecyclerViewAdapter(View root){
//        RecyclerView recyclerView = root.findViewById(R.id.rvDataBayi);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(fotoBayi,namaBayi, detailBayi, getContext(),f_manager);
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//    private void getDataFromInternet(View root){
//        namaBayi.add("Bayi Satu");
//        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748829-bayi-dikelilingi-bunga-gaba-.jpg");
//        detailBayi.add("Ini detail bayi");
//
//        namaBayi.add("Bayi Dua");
//        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748832-bayi-dikelilingi-bunga-gaba-.jpg");
//        detailBayi.add("Ini detail bayi");
//
//        namaBayi.add("Bayi Tiga");
//        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748833-bayi-dikelilingi-bunga-gaba-.jpg");
//        detailBayi.add("Ini detail bayi");
//
//        prosesRecyclerViewAdapter(root);
//    }

    public void getDataBayi(){

        ApiService ardData = UtilsApi.getAPIService();
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(getContext(), "Kode : "+kode+"Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                adData = new RecyclerViewAdapter(getContext(), listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
