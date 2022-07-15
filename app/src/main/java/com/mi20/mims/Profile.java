package com.mi20.mims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mi20.mims.Utils.RestApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends Activity {
    String nim;
    TextView txt_nim, txt_nama;
    EditText txt_kelas, txt_no_hp, txt_alamat, txt_password;
    CardView btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = getIntent();
        nim = i.getStringExtra("nim");
//        Toast.makeText(this, ""+nim, Toast.LENGTH_SHORT).show();
        initview();
        getProfile(nim);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this, "Dalam Perbaikan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getProfile(String nim){
        String url = RestApi.api_mhs_detail;

        StringRequest reqData = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            JSONArray jsonArray = data.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++){
//                                Toast.makeText(Profile.this, ""+jsonArray.getString(1), Toast.LENGTH_SHORT).show();

                                txt_nim.setText(jsonArray.getString(0));
                                txt_nama.setText(jsonArray.getString(1));
                                txt_kelas.setText(jsonArray.getString(7));
                                txt_no_hp.setText(jsonArray.getString(3));
                                txt_alamat.setText(jsonArray.getString(4));
                                txt_password.setText(jsonArray.getString(5));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Profile.this, "Error at "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Profile.this, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nim", nim);

                return map;
            }
        };
        Volley.newRequestQueue(Profile.this).add(reqData);

    }

    public void initview(){
        txt_nim = findViewById(R.id.nim);
        txt_nama = findViewById(R.id.nama);
        txt_kelas = findViewById(R.id.kelas);
        txt_no_hp = findViewById(R.id.no_hp);
        txt_alamat = findViewById(R.id.alamat);
        txt_password = findViewById(R.id.password);
        btn_simpan = findViewById(R.id.simpan);
    }
}