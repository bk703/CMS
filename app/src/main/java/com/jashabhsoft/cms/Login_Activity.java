package com.jashabhsoft.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity {
    EditText num,pass;
    Button login,signup;
    TextView forget_pass;
    boolean valid = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        num=findViewById(R.id.et_login_num);
        pass=findViewById(R.id.et_login_pass);
        login=findViewById(R.id.login_btn);
        forget_pass=findViewById(R.id.forget_pass);
        signup=findViewById(R.id.signup_btn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            validate();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login_Activity.this,Register_Activity.class);
                startActivity(i);
            }
        });
    }
    public void validate()
    {
        String u_pass = pass.getText().toString();
        String u_num = num.getText().toString();


        if (u_num.isEmpty() ) {
            num.setError("Enter number");
            valid = false;
        }
        else if (u_pass.isEmpty()) {
            pass.setError("Enter password");
            valid = false;
        }
        else{
            callapi();
        }
    }
    public void callapi()
    {

        AndroidNetworking.post("https://jashabhsoft.com/Tanker/login.php?contact="+num.getText().toString()+"&password="+pass.getText().toString()+"")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("error").equals("true"))
                            {
                                Toast.makeText(Login_Activity.this, (response.getString("error_msg")), Toast.LENGTH_SHORT).show();
                            }
                            else if(response.getString("error").equals("false"))
                            {

                                Toast.makeText(Login_Activity.this,"Login Successfully" , Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Login_Activity.this,Dashboard.class);
                                startActivity(i);

                                // getText() SHOULD NOT be static!!!startActivity(intent);
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