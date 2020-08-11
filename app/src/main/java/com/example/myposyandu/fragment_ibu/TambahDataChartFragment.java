package com.example.myposyandu.fragment_ibu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myposyandu.R;
import com.example.myposyandu.SharedPrefManager;
import com.example.myposyandu.activity.DetailBayiActivity;
import com.example.myposyandu.activity.Main2Activity;
import com.example.myposyandu.helper.ApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahDataChartFragment extends Fragment {
    EditText etUsia, etBB, etTB;
    Button btnTambah;
    TextView tgl;

    Context mContext;
    ProgressDialog loading;
    ApiService mApiService;
    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_data_chart, container, false);

        inItComponents(view);

        sharedPrefManager = new SharedPrefManager(getActivity());
        String id_bayi = sharedPrefManager.getSpId();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                String usia_bayi = etUsia.getText().toString().trim();
                String berat_bayi = etBB.getText().toString().trim();
                String tinggi_bayi = etTB.getText().toString().trim();

                inputData(id_bayi, usia_bayi, berat_bayi, tinggi_bayi);
            }
        });
        return view;
    }

    private void inItComponents(View view){
        etUsia = view.findViewById(R.id.etUsia);
        etBB = view.findViewById(R.id.etBerat);
        etTB = view.findViewById(R.id.etTinggi);
        btnTambah = view.findViewById(R.id.btnTambah);
        tgl =view.findViewById(R.id.tgl);
    }

    private void ambilTanggal(){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        Toast.makeText(mContext, formattedDate, Toast.LENGTH_SHORT).show();

        tgl.setText(formattedDate);
    }

    private void inputData(final String id_bayi, final String usia_bayi, final String berat_bayi, final String tinggi_bayi){
        mApiService.inputDetailBayi(id_bayi, usia_bayi, berat_bayi, tinggi_bayi)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    showMessage("Data Bayi telah ditambahkan");
                                    Intent intent = new Intent(mContext, DetailBayiActivity.class);
                                    getContext().startActivity(intent);
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    showMessage(error_message);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        showMessage("Koneksi Internet Bermasalah");
                    }
                });
    }

    private void showMessage(String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
