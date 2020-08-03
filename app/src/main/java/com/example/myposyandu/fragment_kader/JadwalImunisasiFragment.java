package com.example.myposyandu.fragment_kader;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myposyandu.R;

public class JadwalImunisasiFragment extends Fragment {
    EditText nomorTelp, isiPesan;
    Button btnKirim;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jadwal_imunisasi, container, false);
        nomorTelp = root.findViewById(R.id.noTelp);
        isiPesan = root.findViewById(R.id.isiPesan);
        btnKirim = root.findViewById(R.id.btnKirimPesan);
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSmsByManager();
            }
        });
        return root;
    }

    public void sendSmsByManager() {
        try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(nomorTelp.getText().toString(),
                    null,
                    isiPesan.getText().toString(),
                    null,
                    null);
            Toast.makeText(getActivity(), "SMS Berhasil Dikirim!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getActivity(),"Pengiriman SMS Gagal...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

}
