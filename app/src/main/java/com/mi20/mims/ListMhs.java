package com.mi20.mims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mi20.mims.Adapters.DosenAdapter;
import com.mi20.mims.Adapters.MhsAdapter;
import com.mi20.mims.Models.ModelDosen;
import com.mi20.mims.Models.ModelMahasiswa;
import com.mi20.mims.Utils.RestApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMhs extends Activity {
    RecyclerView recyclerView;
    private ArrayList<ModelMahasiswa> mItems;
    private MhsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mhs);
        initview();
        mItems = new ArrayList<ModelMahasiswa>();


        getMahasiswa(this);
    }

    public void getMahasiswa(Context context){
        StringRequest reqData = new StringRequest(Request.Method.GET, RestApi.api_mhs,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(ListDosen.this, ""+response, Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject obj = new JSONObject(response);
                            if (!obj.equals("")){
                                JSONArray jsonArray = obj.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    String nid = data.getString("nim");
                                    String nama_dosen = data.getString("nama");
                                    String kelas = data.getString("kelas");

                                    ModelMahasiswa ms = new ModelMahasiswa(
                                            nid,
                                            nama_dosen,
                                            kelas
                                    );

                                    mItems.add(ms);

                                }
                                mAdapter = new MhsAdapter(context, mItems);
                                recyclerView.setAdapter(mAdapter);
//
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                                recyclerView.setLayoutManager(layoutManager);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ListMhs.this, "Error at "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListMhs.this, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(ListMhs.this).add(reqData);
    }

    public void initview(){
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
    }
}