package com.jashabhsoft.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComplainSubmittedMessage extends AppCompatActivity {
    Button backtohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_submitted_message);
        backtohome=findViewById(R.id.back_to_home);
        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ComplainSubmittedMessage.this,Dashboard.class);
                startActivity(i);
                ComplainSubmittedMessage.this.finish();
            }
        });
    }
}