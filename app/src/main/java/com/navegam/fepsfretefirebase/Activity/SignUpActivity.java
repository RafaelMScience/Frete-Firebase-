package com.navegam.fepsfretefirebase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
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
import com.navegam.fepsfretefirebase.R;
import com.navegam.fepsfretefirebase.Utils.CNPJUtil;
import com.navegam.fepsfretefirebase.Utils.CPFUtil;
import com.navegam.fepsfretefirebase.Utils.NavegamData;

public class SignUpActivity extends AppCompatActivity {

    private EditText  edt_nameOwner, edt_boat,edt_cpf,edt_email,edt_cnpj,edt_loginSign,edt_passwordSign;
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

        //login
        edt_loginSign = findViewById( R.id.edt_loginSign );
        edt_passwordSign = findViewById( R.id.edt_PasswordSign );

        btn_regist = findViewById( R.id.btn_register );

        navegamData = new NavegamData();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        edt_cpf.addTextChangedListener( Mask.Companion.mask("###.###.###-##", edt_cpf));
        edt_cnpj.addTextChangedListener( Mask.Companion.mask( "##.###.###/####-##",edt_cnpj ) );

        edt_loginSign.setFilters(new InputFilter[] {new InputFilter.AllCaps(  )});

        btn_regist.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( /*CPFUtil.Companion.myValidateCPF( edt_cpf.getText().toString().trim())
                        && CNPJUtil.Companion.isCNPJ( edt_cnpj.getText().toString().trim() )*/true) {

                    //Date save information in Firebase
                    SaveData();

                } else {

                    Toast.makeText( SignUpActivity.this, "Verifique CPF/CNPJ", Toast.LENGTH_SHORT).show();
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

        //user and password
        navegamData.setLogin( edt_loginSign.getText().toString().trim() );
        navegamData.setPassword( edt_passwordSign.getText().toString().trim() );

        //admin
        navegamData.setAdminOwner( "owner" );

        databaseReference.child( "Navegam" ).child( navegamData.getLogin() ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText( SignUpActivity.this, "Esse cadastro já existe", Toast.LENGTH_SHORT ).show();

                }else {
                    databaseReference.child( "Navegam" ).child( navegamData.getLogin() ).setValue( navegamData ).addOnCompleteListener( new OnCompleteListener<Void>() {
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
