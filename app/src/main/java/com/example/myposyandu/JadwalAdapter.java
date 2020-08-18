package com.example.myposyandu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.myposyandu.activity.DetailBayiActivity;
import com.example.myposyandu.activity.DetailJadwalActivity;
import com.example.myposyandu.helper.UtilsApi;
import com.example.myposyandu.model.ModelDataBayi;
import com.example.myposyandu.model.ModelDataJadwal;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private Context ctx;
    private List<ModelDataJadwal> ListData;

    public JadwalAdapter(Context ctx, List<ModelDataJadwal> listData) {
        this.ctx = ctx;
        ListData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desain_layout_jadwal, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelDataJadwal dm = ListData.get(position);
        holder.tvNama.setText("Jadwal Imunisasi "+dm.getNama_imunisasi());
        holder.tvTanggal.setText("Tanggal :"+dm.getTgl_imunisasi()+", Pukul : "+dm.getWaktu());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailJadwalActivity.class);
                intent.putExtra("id_jadwal", dm.getId_jadwal());
                intent.putExtra("nama_imunisasi", dm.getNama_imunisasi());
                intent.putExtra("tanggal", dm.getTgl_imunisasi());
                intent.putExtra("waktu", dm.getWaktu());
                ctx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
//        return namaBayi.size();
        return ListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama, tvTanggal;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.namaImunisasi);
            tvTanggal = itemView.findViewById(R.id.tglImunisasi);
            constraintLayout = itemView.findViewById(R.id.constraintLayout3);
        }
    }
}
