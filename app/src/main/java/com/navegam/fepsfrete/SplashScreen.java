package com.navegam.fepsfrete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.navegam.fepsfrete.Activity.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );

        final Intent i = new Intent(this, LoginActivity.class);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                finally {
                    startActivity(i);
                    finish();
                }
            }
        };

        timer.start();
    }
}
