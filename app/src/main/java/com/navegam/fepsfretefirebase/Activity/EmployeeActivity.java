package com.navegam.fepsfretefirebase.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.navegam.fepsfretefirebase.R;

public class EmployeeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_employee );

        toolbar = findViewById( R.id.toolbarProducts );
        setSupportActionBar( toolbar );
        setTitle( "Produtos" );
    }

    public void Register_Produto(View view) {
        Intent i = new Intent( EmployeeActivity.this, ProductsRegisterActivity.class );
        startActivity( i );
        onStop();
    }
}
