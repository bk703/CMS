package com.jashabhsoft.cms;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView im;
    private static final String TAG = MainActivity.class.getSimpleName();

    private final int SPLASH_DISPLAY_LENGTH = 3500;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        im = (ImageView) findViewById(R.id.im);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash);
        im.startAnimation(animation);



        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent startActivityIntent = new Intent(MainActivity.this,Register_Activity.class);
                startActivity(startActivityIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);



    }



}
