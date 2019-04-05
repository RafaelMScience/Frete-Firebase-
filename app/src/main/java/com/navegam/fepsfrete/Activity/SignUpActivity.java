package com.navegam.fepsfrete.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navegam.cpfvalidador.Mask;
import com.navegam.fepsfrete.R;
import com.navegam.fepsfrete.Utils.CPFUtil;
import com.navegam.fepsfrete.Utils.NavegamData;

public class SignUpActivity extends AppCompatActivity {

    private EditText  edt_nameOwner, edt_boat,edt_cpf,edt_email,edt_cnpj;
    private NavegamData navegamData;
    private Button btn_regist;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );


        edt_nameOwner = findViewById( R.id.edt_ownerBoat );
        edt_boat = findViewById( R.id.edt_nameBoat );
        edt_cnpj = findViewById( R.id.edt_CNPJ );
        edt_email = findViewById( R.id.edt_email );
        edt_cpf = findViewById( R.id.edt_CPF );

        btn_regist = findViewById( R.id.btn_register );

        navegamData = new NavegamData();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        edt_cpf.addTextChangedListener( Mask.Companion.mask("###.###.###-##", edt_cpf));

        btn_regist.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CPFUtil.Companion.myValidateCPF(edt_cpf.getText().toString().trim())) {

                    //Date save information in Firebase
                    SaveData();

                } else {

                    Toast.makeText( SignUpActivity.this, "CPF inválido", Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }

    private void SaveData() {
        navegamData.setNameBoat( edt_boat.getText().toString().trim() );
        navegamData.setEmail( edt_email.getText().toString().trim() );
        navegamData.setCPF( edt_cpf.getText().toString().trim() );
        navegamData.setCNPJ( edt_cnpj.getText().toString().trim() );
        navegamData.setNameOwner( edt_nameOwner.getText().toString() );

        databaseReference.child( "Navegam" ).child( navegamData.getNameBoat() ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText( SignUpActivity.this, "existe", Toast.LENGTH_SHORT ).show();

                }else {
                    databaseReference.child( "Navegam" ).child( navegamData.getNameBoat() ).child( navegamData.getNameOwner() ).setValue( navegamData ).addOnCompleteListener( new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText( SignUpActivity.this, "Dados Salvo", Toast.LENGTH_SHORT ).show();
                                Intent login = new Intent( SignUpActivity.this, LoginActivity.class );
                                startActivity( login );
                                finish();
                            } else {
                                Toast.makeText( SignUpActivity.this, "Näo foi possível salvar", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText( SignUpActivity.this, "Problemas no Servidor", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
