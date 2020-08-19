package com.example.myposyandu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myposyandu.activity.DetailBayiActivity;
import com.example.myposyandu.activity.DetailIbuActivity;
import com.example.myposyandu.model.ModelDataIbu;

import java.util.List;

public class DataIbuAdapter extends RecyclerView.Adapter<DataIbuAdapter.ViewHolder> {

    private Context ctx;
    private List<ModelDataIbu> ListData;

    public DataIbuAdapter(Context ctx, List<ModelDataIbu> listData) {
        this.ctx = ctx;
        ListData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desain_layout_ibu, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataIbuAdapter.ViewHolder holder, int position) {
        ModelDataIbu dm = ListData.get(position);
        holder.tvNama.setText(dm.getNama());
        holder.tvTelp.setText(dm.getNo_telp());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailIbuActivity.class);
                intent.putExtra("id", dm.getId());
                intent.putExtra("nama", dm.getNama());
                intent.putExtra("username", dm.getEmail());
                intent.putExtra("telp", dm.getNo_telp());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama, tvTelp;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaIbu);
            tvTelp = itemView.findViewById(R.id.tvNoTelp);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutIbu);
        }
    }
}
