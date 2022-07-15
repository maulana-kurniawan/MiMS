package com.mi20.mims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DataAkademisi extends Activity {

    CardView dosen, mhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_akademisi);
        initview();

        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataAkademisi.this, ListDosen.class));
            }
        });

        mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataAkademisi.this, ListMhs.class));
            }
        });
    }

    public void initview(){
        dosen = findViewById(R.id.dosen);
        mhs = findViewById(R.id.mhs);
    }
}