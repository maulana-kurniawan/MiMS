package com.mi20.mims.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mi20.mims.DetailDataAkademisi;
import com.mi20.mims.Models.ModelDosen;
import com.mi20.mims.Models.ModelMahasiswa;
import com.mi20.mims.R;

import java.util.ArrayList;

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.MhsAdapterHolder> {

    private Context context;
    private ArrayList<ModelMahasiswa> mMhs;

    public MhsAdapter(Context context, ArrayList<ModelMahasiswa> mMhs) {
        this.context = context;
        this.mMhs = mMhs;
    }

    @NonNull
    @Override
    public MhsAdapter.MhsAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_mhs, null);

        return new MhsAdapter.MhsAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsAdapter.MhsAdapterHolder holder, int position) {
        holder.nama.setText(mMhs.get(position).getNama());
        holder.kelas.setText(mMhs.get(position).getKelas());
        holder.id = mMhs.get(position).getNim();
        holder.nim.setText(holder.id);

        holder.itemView.setTag(holder.id);
    }

    @Override
    public int getItemCount() {
        return (mMhs != null) ? mMhs.size() : 0;
    }

    public class MhsAdapterHolder extends RecyclerView.ViewHolder{

        public TextView nim, nama, kelas;
        CardView btn_list;
        String id;

        public MhsAdapterHolder(@NonNull View itemView) {
            super(itemView);

            nim = (TextView) itemView.findViewById(R.id.nim);
            nama = (TextView) itemView.findViewById(R.id.nama);
            kelas = (TextView) itemView.findViewById(R.id.kelas);
            btn_list = (CardView) itemView.findViewById(R.id.btn_list);

            btn_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailDataAkademisi.class);
                    i.putExtra("key", id);
                    i.putExtra("status", "mahasiswa");
                    context.startActivity(i);

//                    ((ListDosen)context).finish();
                }
            });
        }
    }
}
