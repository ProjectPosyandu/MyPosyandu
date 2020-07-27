package com.example.myposyandu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBayiActivity extends AppCompatActivity {
    Button btnTambah;
    EditText etNama;
    TextView tvId, tvTgl;

    ProgressDialog loading;

    Context mContext;
    ApiService mApiService;
    SharedPrefManager sharedPrefManager;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button btDatePicker;

    RadioGroup rgGender;
    RadioButton cowok, cewek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_bayi);

        inItComponents();
        sharedPrefManager = new SharedPrefManager(this);
        String id = sharedPrefManager.getSpId();
        tvId.setText(id);

        //pilih tanggal
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                String nama_bayi = etNama.getText().toString().trim();
                String tgl_lahir = tvTgl.getText().toString().trim();
                String jenis_kelamin = null;
                String id = sharedPrefManager.getSpId();
//                String id_u = tvId.getText().toString().trim();
//                int id_user=Integer.parseInt(id_u);

                if(cowok.isChecked()){
                    jenis_kelamin = "Laki-Laki";
                }else if(cewek.isChecked()){
                    jenis_kelamin = "Perempuan";
                }else{
                    Toast.makeText(getApplicationContext(), "Tidak Ada Yang Dipilih", Toast.LENGTH_SHORT).show();
                }

//                Toast.makeText(mContext,"Clicked "+jenis_kelamin+" "+tgl_lahir, Toast.LENGTH_SHORT).show();

                if ( nama_bayi.isEmpty() || tgl_lahir.isEmpty() || jenis_kelamin.isEmpty()){
                    showMessage("Field belum terisi. Mohon lengkapi semua field isian diatas");
                }else {
                    inputBayi(nama_bayi,tgl_lahir,jenis_kelamin,id);
                }

            }
        });
    }

    private void inItComponents() {
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        etNama = findViewById(R.id.daNama);
        btDatePicker = (Button) findViewById(R.id.btnTanggal);
        rgGender = (RadioGroup) findViewById(R.id.rbJenis);
        cowok = (RadioButton) findViewById(R.id.rbCowok);
        cewek = (RadioButton) findViewById(R.id.rbCewek);
        tvId = findViewById(R.id.tvId);
        tvTgl = findViewById(R.id.tvTgl);

        btnTambah = findViewById(R.id.btnTambah);
    }

    private void showDateDialog(){

        /**Calendar untuk mendapatkan tanggal sekarang*/
        Calendar newCalendar = Calendar.getInstance();

        /**Initiate DatePicker dialog*/
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 * Set Calendar untuk menampung tanggal yang dipilih */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**Update TextView dengan tanggal yang kita pilih */
                String tgl = dateFormatter.format(newDate.getTime());
                btDatePicker.setText(tgl);
                tvTgl.setText(tgl);
//                String tgl_lahir = dateFormatter.format(newDate.getTime());
//                Toast.makeText(mContext, "Tanggal"+tgl_lahir, Toast.LENGTH_SHORT).show();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**Tampilkan DatePicker dialog*/
        datePickerDialog.show();
    }

    private void inputBayi(final String nama_bayi, final String tgl_lahir, final String jenis_kelamin,final String id) {
        mApiService.inputBayiRequest(nama_bayi,tgl_lahir,jenis_kelamin, id)
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
