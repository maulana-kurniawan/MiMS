package com.mi20.mims;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class DetailDataAkademisi extends Activity {
    String key, status;
    TextView txt_nim;
    EditText nim, nama, alamat, no_hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_akademisi);
        initview();

        Intent i = getIntent();
        key = i.getStringExtra("key");
        status = i.getStringExtra("status");
//        Toast.makeText(this, ""+key, Toast.LENGTH_SHORT).show();
        getDetail();
    }

    public void getDetail(){
        if (status.equals("dosen")){
            String url = RestApi.api_dosen_detail;
            StringRequest reqData = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject data = new JSONObject(response);
                                JSONArray jsonArray = data.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++){
//                                        Toast.makeText(DetailDataAkademisi.this, ""+jsonArray.getString(1), Toast.LENGTH_SHORT).show();

                                    txt_nim.setText("NID");
                                    nim.setText(jsonArray.getString(0));
                                    nama.setText(jsonArray.getString(1));
                                    alamat.setText(jsonArray.getString(3));
                                    no_hp.setText(jsonArray.getString(2));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(DetailDataAkademisi.this, "Error at "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DetailDataAkademisi.this, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("nid", key);

                    return map;
                }
            };
            Volley.newRequestQueue(DetailDataAkademisi.this).add(reqData);

        }else if(status.equals("mahasiswa")){
            String url = RestApi.api_mhs_detail;
            StringRequest reqData = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject data = new JSONObject(response);
                                JSONArray jsonArray = data.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++){
//                                        Toast.makeText(DetailDataAkademisi.this, ""+jsonArray.getString(1), Toast.LENGTH_SHORT).show();

                                    txt_nim.setText("NIM");
                                    nim.setText(jsonArray.getString(0));
                                    nama.setText(jsonArray.getString(1));
                                    alamat.setText(jsonArray.getString(4));
                                    no_hp.setText(jsonArray.getString(3));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(DetailDataAkademisi.this, "Error at "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DetailDataAkademisi.this, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("nim", key);

                    return map;
                }
            };
            Volley.newRequestQueue(DetailDataAkademisi.this).add(reqData);
        }else{

        }
    }

    public void initview(){
        txt_nim = findViewById(R.id.txt_nim);
        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        no_hp = findViewById(R.id.no_hp);
    }
}