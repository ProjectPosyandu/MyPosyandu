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
import androidx.recyclerview.widget.RecyclerView;

import com.example.myposyandu.model.ModelDataBayi;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

//    private ArrayList<String> fotoBayi = new ArrayList<>();
//    private ArrayList<String> namaBayi = new ArrayList<>();
//    private ArrayList<String> detailBayi = new ArrayList<>();
//    public FragmentManager f_manager;
    private Context ctx;
    private List<ModelDataBayi> ListData;


//    public RecyclerViewAdapter(ArrayList<String> fotoBayi, ArrayList<String> namaBayi, ArrayList<String> detailBayi, Context context, FragmentManager f_manager ) {
//        this.fotoBayi = fotoBayi;
//        this.namaBayi = namaBayi;
//        this.detailBayi = detailBayi;
//        this.context = context;
//        this.f_manager = f_manager;
//    }


    public RecyclerViewAdapter(Context ctx, List<ModelDataBayi> listData) {
        this.ctx = ctx;
        ListData = listData;
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
//        Glide.with(context).asBitmap().load(fotoBayi.get(position)).into(holder.imageView2);
//        holder.tvnamaBayi.setText(namaBayi.get(position));
//        ModelDataBayi debay = listBayi.get(position);
        ModelDataBayi dm = ListData.get(position);
        holder.tvnamaBayi.setText(dm.getNama_bayi());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ctx, dm.getNama_bayi(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ctx, DetailBayiActivity.class);
                intent.putExtra("nama_bayi", dm.getNama_bayi());
                intent.putExtra("jenis_kelamin", dm.getJenis_kelamin());
                ctx.startActivity(intent);

//                f_manager.beginTransaction()
//                        .replace(R.id.nav_host_fragment, new DetailBayiFragment())
//                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
//        return namaBayi.size();
        return ListData.size();
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
