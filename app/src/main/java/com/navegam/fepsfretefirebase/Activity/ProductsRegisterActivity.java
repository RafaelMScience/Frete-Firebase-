package com.navegam.fepsfretefirebase.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.navegam.cpfvalidador.Mask;
import com.navegam.fepsfretefirebase.R;
import com.navegam.fepsfretefirebase.Utils.Document;
import com.navegam.fepsfretefirebase.Utils.NomesDados;

import java.util.HashMap;
import java.util.Map;

public class ProductsRegisterActivity extends AppCompatActivity {

    int i = 0;
    public Button btnEnvia;
    private EditText edtRem,edtValor,edtQtd,edtDestinario,edtTelRem,edtOrig,edtDest,edtViagem,edtDesc
            ,edtVPago,edtVFrete;

    Map<String, Object> user = new HashMap<>();

    private Toolbar toolbar;

    SharedPreferences sharedPreferences;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_products_register );

        btnEnvia = findViewById(R.id.btn_enviar);

        edtRem = findViewById(R.id.edt_Rem);
        edtTelRem = findViewById(R.id.edt_TelRem);
        edtOrig = findViewById(R.id.edt_Orig);
        edtDesc = findViewById(R.id.edt_Desc);
        edtValor = findViewById(R.id.edt_valor);
        edtQtd = findViewById(R.id.edt_Quant);
        edtDestinario = findViewById(R.id.edt_Destinario);
        edtDest = findViewById(R.id.edt_Dest);
        edtViagem = findViewById(R.id.edt_Data);
        edtVPago = findViewById(R.id.edt_VPago);
        edtVFrete = findViewById(R.id.edt_VFrete);

        toolbar = findViewById( R.id.toolbarRegistProct );
        setSupportActionBar( toolbar );
        setTitle( "Cadastro de Produtos" );

        sharedPreferences = getSharedPreferences( "Navegam",MODE_PRIVATE );
        final String boatFunc = sharedPreferences.getString( "boatFunc","" );

        edtViagem.addTextChangedListener( Mask.Companion.mask("##/##/####", edtViagem));


        btnEnvia.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveNote(v);
            }
        } );

    }

    public void saveNote(View view) {
        FirebaseApp.initializeApp( this );
        NomesDados nd = new NomesDados();
        String Title = edtDesc.getText().toString();
        String Destanatario = edtDestinario.getText().toString();
        String Valor = edtValor.getText().toString();
        String Origem = edtOrig.getText().toString();
        String Qtd = edtQtd.getText().toString();
        String TelR = edtTelRem.getText().toString();
        String Rem = edtRem.getText().toString();
        String Viagem = edtViagem.getText().toString();
        String Des = edtDest.getText().toString();
        String ValorPago = edtVPago.getText().toString();
        String ValorFrete = edtVFrete.getText().toString();


        sharedPreferences = getSharedPreferences( "Navegam",MODE_PRIVATE );
        final String boatFunc = sharedPreferences.getString( "boatFunc","" );
        final String nameFunc = sharedPreferences.getString( "nameFunc","" );


        // Create a new user with a first and last name
        Document doc = new Document( Rem, Destanatario, Des, Origem, TelR, Viagem, Title, Qtd, Valor, ValorFrete, ValorPago );
        firestore.collection( "" + boatFunc).document( ""+nameFunc )
                .collection( ""+ TelR.trim() ).document().set( doc );

        Toast.makeText( this, "Salvo com Sucesso", Toast.LENGTH_SHORT ).show();
    }
}
