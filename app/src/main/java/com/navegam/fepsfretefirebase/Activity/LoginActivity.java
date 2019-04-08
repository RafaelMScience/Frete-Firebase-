package com.navegam.fepsfrete.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.navegam.fepsfrete.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
    }

    public void click_regist(View view) {
        Intent signUp = new Intent( LoginActivity.this, SignUpActivity.class );
        startActivity( signUp );
        onStop();
    }
}
