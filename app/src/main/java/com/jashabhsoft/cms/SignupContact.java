package com.jashabhsoft.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupContact extends AppCompatActivity {
    EditText contact;
    Button activate;
    String fname;
    String lname;
    String gender;
    String email;
    String address;
    String cnic;
    String password;
    boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_contact);
        contact=findViewById(R.id.et_contact);
        activate=findViewById(R.id.activate);
        fname = getIntent().getExtras().getString("fname");
        lname = getIntent().getExtras().getString("lname");
        gender = getIntent().getExtras().getString("gender");
        email = getIntent().getExtras().getString("email");
        address = getIntent().getExtras().getString("address");
        cnic = getIntent().getExtras().getString("cnic");
        password = getIntent().getExtras().getString("password");



        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }
    public void validate()
    {
        String u_contact = contact.getText().toString();


        if (u_contact.isEmpty() || u_contact.length() < 3) {
            contact.setError("at least 3 characters");
            valid = false;
        }
        else{
            callapi();
        }
    }
    public void callapi()
    {
        AndroidNetworking.post("https://jashabhsoft.com/Tanker/chkcontact.php?contact="+contact.getText().toString()+"")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("error").equals("true"))
                            {
                                Toast.makeText(SignupContact.this, "Number already registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                uploaddata();

                                // getText() SHOULD NOT be static!!!startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }
    void uploaddata()
    {
        AndroidNetworking.post("https://jashabhsoft.com/Tanker/register.php?email="+email+"&password="+password+"&contact="+contact.getText().toString()+"&cnic="+cnic+"&f_name="+fname+"&l_name="+lname+"&gender="+gender+"&address="+address+"")

                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            if(response.getString("error").equals("true"))
                            {
                                Toast.makeText(SignupContact.this, "User already registered", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.getString("error").equals("false"))
                            {
                                    Toast.makeText(SignupContact.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}