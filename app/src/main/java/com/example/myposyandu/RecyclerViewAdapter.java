package com.example.myposyandu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myposyandu.ui.DetailBayiFragment;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> fotoBayi = new ArrayList<>();
    private ArrayList<String> namaBayi = new ArrayList<>();
    private ArrayList<String> detailBayi = new ArrayList<>();
    private Context context;
    public FragmentManager f_manager;

    public RecyclerViewAdapter(ArrayList<String> fotoBayi, ArrayList<String> namaBayi, ArrayList<String> detailBayi, Context context, FragmentManager f_manager ) {
        this.fotoBayi = fotoBayi;
        this.namaBayi = namaBayi;
        this.detailBayi = detailBayi;
        this.context = context;
        this.f_manager = f_manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desain_layout_adapter, parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(fotoBayi.get(position)).into(holder.imageView2);
        holder.tvnamaBayi.setText(namaBayi.get(position));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, namaBayi.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DetailBayiActivity.class);
                intent.putExtra("foto_bayi", fotoBayi.get(position));
                intent.putExtra("nama_bayi", namaBayi.get(position));
                intent.putExtra("detail_bayi", detailBayi.get(position));
                context.startActivity(intent);

//                f_manager.beginTransaction()
//                        .replace(R.id.nav_host_fragment, new DetailBayiFragment())
//                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return namaBayi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView2;
        TextView tvnamaBayi;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView2 = itemView.findViewById(R.id.imageView2);
            tvnamaBayi = itemView.findViewById(R.id.tvNamaBayi);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
