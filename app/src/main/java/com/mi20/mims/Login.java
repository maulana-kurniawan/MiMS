package com.mi20.mims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mi20.mims.Utils.AppController;
import com.mi20.mims.Utils.RestApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {

    EditText nim, password;
    CardView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Login.this, MainActivity.class);
//                startActivity(i);
                _login();
            }
        });
    }

    public void _login(){
        String url = RestApi.api_login;

        StringRequest reqData = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            String nim = obj.getString("nim");
                            String nama = obj.getString("name");

//                            Toast.makeText(Login.this, ""+obj, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this, MainActivity.class);
                            i.putExtra("nim", nim);
                            i.putExtra("nama", nama);
                            startActivity(i);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, "Error at "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Error at "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("nim", nim.getText().toString());
                map.put("password", password.getText().toString());

                return map;
            }
        };
        Volley.newRequestQueue(Login.this).add(reqData);



    }

    public void initview(){
        nim = findViewById(R.id.nim);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
    }
}