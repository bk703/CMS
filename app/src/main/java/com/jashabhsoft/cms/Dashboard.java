package com.jashabhsoft.cms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {
    CardView carpenter,tanker,plumber,elec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        carpenter=findViewById(R.id.carpenter);
        tanker=findViewById(R.id.tanker);
        plumber=findViewById(R.id.plumber);
        elec=findViewById(R.id.electrition);
        carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dashboard.this,WriteComplain.class);
                startActivity(i);
            }
        });
        tanker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dashboard.this,WriteComplain.class);
                startActivity(i);
                Dashboard.this.finish();
            }
        });
        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dashboard.this,WriteComplain.class);
                startActivity(i);
                Dashboard.this.finish();
            }
        });
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Dashboard.this,WriteComplain.class);
                startActivity(i);
                Dashboard.this.finish();

            }
        });
    }
}