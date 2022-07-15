package com.mi20.mims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mi20.mims.Utils.RestApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddJadwal extends Activity {
    String id_kelas;
    EditText hari, matkul, pengampu, ruangan, waktu;
    CardView simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jadwal);
        initview();

        Intent i = getIntent();
        id_kelas = i.getStringExtra("id_kelas");

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _addJadwal(id_kelas);
            }
        });
    }

    public void _addJadwal(String id_kelas){
        String url = RestApi.api_add_jadwal;

        StringRequest reqData = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddJadwal.this, "Berhasil menambah jadwal pengganti", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddJadwal.this, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_kelas", id_kelas);
                map.put("hari", hari.getText().toString());
                map.put("matkul", matkul.getText().toString());
                map.put("pengampu", pengampu.getText().toString());
                map.put("ruang", ruangan.getText().toString());
                map.put("waktu", waktu.getText().toString());

                return map;
            }
        };
        Volley.newRequestQueue(AddJadwal.this).add(reqData);
    }

    public void initview(){
        hari = findViewById(R.id.hari);
        matkul = findViewById(R.id.matkul);
        pengampu = findViewById(R.id.pengampu);
        ruangan = findViewById(R.id.ruangan);
        waktu = findViewById(R.id.waktu);
        simpan = findViewById(R.id.simpan);
    }
}