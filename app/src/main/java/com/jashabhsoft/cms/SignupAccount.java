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

public class SignupAccount extends AppCompatActivity {
    EditText cnic,pass,confirmpass;
    String fname ;
    String lname ;
    String gender ;
    String email;
    String address;
    Button accuoutnext;
    boolean valid = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_account);
        cnic=findViewById(R.id.et_cnic);
        pass=findViewById(R.id.et_singup_pass);
        confirmpass=findViewById(R.id.et_singup_confirmpass);
        accuoutnext=findViewById(R.id.account_next);
     fname = getIntent().getExtras().getString("fname");
        lname = getIntent().getExtras().getString("lname");
         gender = getIntent().getExtras().getString("gender");
         email = getIntent().getExtras().getString("email");
         address = getIntent().getExtras().getString("address");




        accuoutnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

            }
        });
    }
    public void validate()
    {
        String u_pass = pass.getText().toString();
        String u_confirm = confirmpass.getText().toString();
        String u_cnic = cnic.getText().toString();

        if (u_cnic.isEmpty() || u_cnic.length() < 3) {
            cnic.setError("at least 3 characters");
            valid = false;
        }
        else if (u_pass.isEmpty() || u_pass.length() < 3) {
            pass.setError("at least 3 characters");
            valid = false;
        }
       else if (u_confirm.isEmpty() || u_confirm.length() < 3) {
            confirmpass.setError("at least 3 characters");
            valid = false;
        } else if (!u_pass.equals(u_confirm)){
            confirmpass.setError("Password Not matched");
            pass.setError("Password Not matched");
        }
       else{
           callapi();
        }
    }
    void callapi()
    {
        AndroidNetworking.post("https://jashabhsoft.com/Tanker/chkcnic.php?cnic="+cnic.getText().toString()+"")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("error").equals("true"))
                            {
                                Toast.makeText(SignupAccount.this, "Cnic already registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                Intent intent = new Intent(SignupAccount.this, SignupContact.class);
                                intent.putExtra("fname",fname );
                                intent.putExtra("email", email);
                                intent.putExtra("lname",lname );
                                intent.putExtra("address", address);
                                intent.putExtra("gender", gender);
                                intent.putExtra("cnic", cnic.getText().toString());
                                intent.putExtra("password",pass.getText().toString());

                                // getText() SHOULD NOT be static!!!
                                startActivity(intent);
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
}