package com.jashabhsoft.cms;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class Register_Activity extends AppCompatActivity {
  EditText fname,lname,address,email;
  RadioGroup radioGroup;
    Button personal_next;
    String gender;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        personal_next=findViewById(R.id.personal_next);
        fname=findViewById(R.id.et_fname);
        lname=findViewById(R.id.et_l_name);
        address=findViewById(R.id.et_address);
        email=findViewById(R.id.et_email);
        radioGroup=findViewById(R.id.rgroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.r_male) {
                   gender ="male";
                } else if(checkedId == R.id.r_female) {
                    gender="female";
                }
            }

        });


        personal_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            validate();

            }
        });

    }
public void validate()
{
    String u_fname = fname.getText().toString();
    String u_email = email.getText().toString();
    String u_lname = lname.getText().toString();
    String u_address = address.getText().toString();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    if (u_email.isEmpty() || u_email.length() < 3 || u_email.matches(emailPattern)) {
        email.setError("Email Required");
        email.findFocus();
        valid = false;
    } else {
        Intent intent = new Intent(Register_Activity.this, SignupAccount.class);
        intent.putExtra("fname",fname.getText().toString() );
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("lname",lname.getText().toString() );
        intent.putExtra("address", address.getText().toString());
        intent.putExtra("gender", gender);// getText() SHOULD NOT be static!!!
        startActivity(intent);
    }
}
}