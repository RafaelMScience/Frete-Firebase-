package com.navegam.fepsfretefirebase.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.navegam.fepsfretefirebase.R;
import com.navegam.fepsfretefirebase.Utils.Document;
import com.navegam.fepsfretefirebase.Utils.NomesDados;

import java.util.HashMap;
import java.util.Map;

public class ProductsRegisterActivity extends AppCompatActivity {

    int i = 0;
    public Button btnEnvia;
    private EditText edtRem,edtValor,edtQtd,edtDestinario,edtTelRem,edtOrig,edtDest,edtvigem,edtDesc
            ,edtVPago,edtVFrete;

    Map<String, Object> user = new HashMap<>();

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
        edtvigem = findViewById(R.id.edt_Data);
        edtVPago = findViewById(R.id.edt_VPago);
        edtVFrete = findViewById(R.id.edt_VFrete);

        btnEnvia.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference frete = firestore.collection(""+edtDest.getText().toString());
                frete.document(""+i).set(user);

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
        String Viagem = edtvigem.getText().toString();
        String Des = edtDest.getText().toString();
        String ValorPago = edtVPago.getText().toString();
        String ValorFrete = edtVFrete.getText().toString();

        // Create a new user with a first and last name
        Document doc = new Document( Rem, Destanatario, Des, Origem, TelR, Viagem, Title, Qtd, Valor, ValorFrete, ValorPago );
        firestore.collection( "" + edtDest.getText().toString() ).document( ""+i ).set( doc );

        Toast.makeText( this, "Salvo com Sucesso", Toast.LENGTH_SHORT ).show();
    }
}
