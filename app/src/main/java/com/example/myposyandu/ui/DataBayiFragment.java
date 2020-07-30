package com.example.myposyandu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myposyandu.R;
import com.example.myposyandu.RecyclerViewAdapter;

import java.util.ArrayList;

public class DataBayiFragment extends Fragment {
    private ArrayList<String> fotoBayi = new ArrayList<>();
    private ArrayList<String> namaBayi = new ArrayList<>();
    private ArrayList<String> detailBayi = new ArrayList<>();
    private FragmentManager f_manager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_data_bayi, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("Disini data bayi");
        getDataFromInternet(root);

        return root;
    }

    private void prosesRecyclerViewAdapter(View root){
        RecyclerView recyclerView = root.findViewById(R.id.rvDataBayi);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(fotoBayi,namaBayi, detailBayi, getContext(),f_manager);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getDataFromInternet(View root){
        namaBayi.add("Bayi Satu");
        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748829-bayi-dikelilingi-bunga-gaba-.jpg");
        detailBayi.add("Ini detail bayi");

        namaBayi.add("Bayi Dua");
        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748832-bayi-dikelilingi-bunga-gaba-.jpg");
        detailBayi.add("Ini detail bayi");

        namaBayi.add("Bayi Tiga");
        fotoBayi.add("https://cdn-brilio-net.akamaized.net/news/2018/03/08/139907/748833-bayi-dikelilingi-bunga-gaba-.jpg");
        detailBayi.add("Ini detail bayi");

        prosesRecyclerViewAdapter(root);
    }

}
