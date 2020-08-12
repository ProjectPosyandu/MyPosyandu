package com.example.myposyandu.fragment_kader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.myposyandu.ArtikelAdapter;
import com.example.myposyandu.R;
import com.example.myposyandu.SharedPrefManager;
import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.UtilsApi;
import com.example.myposyandu.model.ModelDataArtikel;
import com.example.myposyandu.model.ResponseModelArtikel;

import java.util.ArrayList;
import java.util.List;

public class ArtikelAdminFragment extends Fragment {

    Button tambah;
    private RecyclerView rvArtikel;
    private RecyclerView.Adapter adArtikel;
    private RecyclerView.LayoutManager lmArtikel;
    private List<ModelDataArtikel> listData = new ArrayList<>();
    TextView status;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_artikel_admin, container, false);
        tambah  = root.findViewById(R.id.btnTambahArtikel);
        rvArtikel = root.findViewById(R.id.rvArtikelAdmin);
        status = root.findViewById(R.id.statusArtikelAdmin);
        lmArtikel = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvArtikel.setLayoutManager(lmArtikel);
        getDataArtikel();

        tambah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                                new TambahArtikelFragment()).commit();

                    }
                }
        );
        return root;
    }

    public void getDataArtikel(){
        ApiService ardArtikel = UtilsApi.getAPIService();
        Call<ResponseModelArtikel> tampilArtikel = ardArtikel.getDataArtikel();
        tampilArtikel.enqueue(new Callback<ResponseModelArtikel>() {
            @Override
            public void onResponse(Call<ResponseModelArtikel> call, Response<ResponseModelArtikel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                listData = response.body().getData();
                adArtikel = new ArtikelAdapter(getContext(), listData);
                rvArtikel.setAdapter(adArtikel);
                adArtikel.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelArtikel> call, Throwable t) {

            }
        });

    }
}