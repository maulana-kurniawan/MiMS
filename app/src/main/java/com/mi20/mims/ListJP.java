package com.mi20.mims;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.mi20.mims.Adapters.JPAdapter;
import com.mi20.mims.Adapters.MhsAdapter;
import com.mi20.mims.Models.ModelJP;
import com.mi20.mims.Utils.RestApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListJP extends Activity implements NavigationView.OnNavigationItemSelectedListener{

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView recyclerView;
    private ArrayList<ModelJP> mItems;
    private JPAdapter mAdapter;
    String id_kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_j_p);

        initview();

        navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(ListJP.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(ListJP.this);

        Intent i = getIntent();
        id_kelas = i.getStringExtra("id_kelas");

        mItems = new ArrayList<ModelJP>();


        getJP(this);
    }

    public void getJP(Context context){
        StringRequest reqData = new StringRequest(Request.Method.POST, RestApi.api_jadwal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            if (!obj.equals("")){
                                JSONArray jsonArray = obj.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    String id = data.getString("id_jadwal");
                                    String hari = data.getString("hari");
                                    String matkul = data.getString("matkul");
                                    String pengampu = data.getString("pengampu");
                                    String ruang = data.getString("ruang");
                                    String waktu = data.getString("waktu");

                                    ModelJP mjp = new ModelJP(
                                            hari,
                                            matkul,
                                            pengampu,
                                            ruang,
                                            waktu,
                                            id
                                    );

                                    mItems.add(mjp);

                                }
                                mAdapter = new JPAdapter(context, mItems);
                                recyclerView.setAdapter(mAdapter);
//
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                                recyclerView.setLayoutManager(layoutManager);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ListJP.this, "Error at "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListJP.this, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_kelas", id_kelas);

                return map;
            }
        };
        Volley.newRequestQueue(ListJP.this).add(reqData);
    }

    public void initview(){
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tentang:
                startActivity(new Intent(ListJP.this, Tentang.class));
                break;

            case R.id.logout:
                startActivity(new Intent(ListJP.this, Logout.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}