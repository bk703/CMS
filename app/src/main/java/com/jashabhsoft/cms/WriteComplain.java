package com.jashabhsoft.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteComplain extends AppCompatActivity {
    EditText title,description;
    Button again,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_complain);
        getSupportActionBar().hide();
        title=findViewById(R.id.et_title);
        description=findViewById(R.id.et_description);
        again=findViewById(R.id.write_another_complain);
        submit=findViewById(R.id.submit_complain);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WriteComplain.this,ComplainSubmittedMessage.class);
                startActivity(i);
                WriteComplain.this.finish();
            }
        });
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WriteComplain.this,Dashboard.class);
                startActivity(i);
                WriteComplain.this.finish();
            }
        });
    }
}