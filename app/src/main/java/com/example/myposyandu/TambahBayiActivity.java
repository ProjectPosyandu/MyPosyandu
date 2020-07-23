package com.example.myposyandu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBayiActivity extends AppCompatActivity {
    Button btnTambah;
    EditText etNama, etLahir, etJenis;

    ProgressDialog loading;

    Context mContext;
    ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_bayi);

        inItComponents();
        buttonClick();
    }

    private void inItComponents() {
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        etNama = findViewById(R.id.daNama);
        etLahir = findViewById(R.id.daLahir);
        etJenis = findViewById(R.id.daJenis);
        btnTambah = findViewById(R.id.btnTambah);
    }

    private void buttonClick() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                String nama_bayi = etNama.getText().toString().trim();
                String tgl_lahir = etLahir.getText().toString().trim();
                String jenis_kelamin = etJenis.getText().toString().trim();
                if ( nama_bayi.isEmpty() || tgl_lahir.isEmpty() || jenis_kelamin.isEmpty()){
                    showMessage("Field belum terisi. Mohon lengkapi semua field isian diatas");
                }else {
                    inputBayi(nama_bayi,tgl_lahir,jenis_kelamin);
                }

            }
        });
    }

    private void inputBayi(final String nama_bayi, final String tgl_lahir, final String jenis_kelamin) {
        mApiService.inputBayiRequest(nama_bayi,tgl_lahir,jenis_kelamin)
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
                                    startActivity(new Intent(mContext, Main2Activity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
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
