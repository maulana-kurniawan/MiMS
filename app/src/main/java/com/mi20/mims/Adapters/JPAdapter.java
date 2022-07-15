package com.mi20.mims.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mi20.mims.DetailDataAkademisi;
import com.mi20.mims.Models.ModelJP;
import com.mi20.mims.R;
import com.mi20.mims.Utils.RestApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JPAdapter extends RecyclerView.Adapter<JPAdapter.JPAdapterHolder> {

    private Context context;
    private ArrayList<ModelJP> mjp;

    public JPAdapter(Context context, ArrayList<ModelJP> mjp) {
        this.context = context;
        this.mjp = mjp;
    }

    @NonNull
    @Override
    public JPAdapter.JPAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_jp, null);

        return new JPAdapter.JPAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JPAdapter.JPAdapterHolder holder, int position) {
        holder.hari.setText(mjp.get(position).getHari());
        holder.matkul.setText(mjp.get(position).getMatkul());
        holder.pengampu.setText(mjp.get(position).getPengampu());
        holder.ruang.setText(mjp.get(position).getRuang());
        holder.waktu.setText(mjp.get(position).getWaktu());
        holder.id = mjp.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return (mjp != null) ? mjp.size() : 0;
    }

    public class JPAdapterHolder extends RecyclerView.ViewHolder{

        public TextView hari, matkul, pengampu, ruang, waktu;
        ImageView btn_delete;
        String id;

        public JPAdapterHolder(@NonNull View itemView) {
            super(itemView);

            hari = (TextView) itemView.findViewById(R.id.hari);
            matkul = (TextView) itemView.findViewById(R.id.matkul);
            pengampu = (TextView) itemView.findViewById(R.id.pengampu);
            ruang = (TextView) itemView.findViewById(R.id.ruang);
            waktu = (TextView) itemView.findViewById(R.id.waktu);
            btn_delete = (ImageView) itemView.findViewById(R.id.btn_delete);

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = RestApi.api_delete_jadwal;
                    StringRequest reqData = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(context, "Data Berhasil Dihapus. Silahkan keluar dan masuk kedalam menu ini kembali", Toast.LENGTH_SHORT).show();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(context, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put("id_jadwal", id);

                            return map;
                        }
                    };
                    Volley.newRequestQueue(context).add(reqData);
                }
            });
        }
    }
}
