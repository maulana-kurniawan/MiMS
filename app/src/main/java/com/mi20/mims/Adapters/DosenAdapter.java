package com.mi20.mims.Adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mi20.mims.DetailDataAkademisi;
import com.mi20.mims.ListDosen;
import com.mi20.mims.Models.ModelDosen;
import com.mi20.mims.R;

import java.util.ArrayList;


public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.DosenAdapterViewHolder> {

    private Context context;
    private ArrayList<ModelDosen> mDosen;

    public DosenAdapter(Context context, ArrayList<ModelDosen> mDosen) {
        this.context = context;
        this.mDosen = mDosen;
    }

    @NonNull
    @Override
    public DosenAdapter.DosenAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_dosen, null);

        return new DosenAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DosenAdapter.DosenAdapterViewHolder holder, int position) {
        holder.nama_dosen.setText(mDosen.get(position).getNama_dosen());
        holder.id = mDosen.get(position).getNid();
        holder.nid.setText(holder.id);

        holder.itemView.setTag(holder.id);
    }

    @Override
    public int getItemCount() {
        return (mDosen != null) ? mDosen.size() : 0;
    }

    public class DosenAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView nid, nama_dosen;
        CardView btn_list;
        String id;

        public DosenAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            nid = (TextView) itemView.findViewById(R.id.nid);
            nama_dosen = (TextView) itemView.findViewById(R.id.nama_dosen);
            btn_list = (CardView) itemView.findViewById(R.id.btn_list);

            btn_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailDataAkademisi.class);
                    i.putExtra("key", id);
                    i.putExtra("status", "dosen");
                    context.startActivity(i);

//                    ((ListDosen)context).finish();
                }
            });



        }
    }
}
