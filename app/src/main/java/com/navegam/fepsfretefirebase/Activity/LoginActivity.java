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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navegam.fepsfretefirebase.R;
import com.navegam.fepsfretefirebase.Utils.NavegamData;

public class LoginActivity extends AppCompatActivity {


    private EditText edt_login,edt_password;
    private Button btn_login;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        edt_login = findViewById( R.id.edt_usarnameLogin );
        edt_password = findViewById( R.id.edt_passwordLogin );

        btn_login = findViewById( R.id.btn_login );

        edt_login.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Navegam" );
        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        } );
    }

    private void Login() {
        String usarname = edt_login.getText().toString();
        final String password = edt_password.getText().toString();

        try {
            databaseReference.child( usarname ).addValueEventListener( new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    NavegamData navegamData = dataSnapshot.getValue( NavegamData.class );
                    assert navegamData != null;
                    if (password.equals( navegamData.getPassword() )) {
                        if(navegamData.getAdminOwner().equals( "owner" )) {
                            Intent i = new Intent( LoginActivity.this, MainActivity.class );
                            startActivity( i );
                        }else if(navegamData.getEmployees().equals( "employess" )){
                            Toast.makeText( LoginActivity.this, "Funcionario", Toast.LENGTH_SHORT ).show();
                        }else{
                            Toast.makeText( LoginActivity.this, "Voce nao tem permissao", Toast.LENGTH_SHORT ).show();
                        }
                    } else {
                        Toast.makeText( LoginActivity.this, "Usuario/Senha incorreto", Toast.LENGTH_SHORT ).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            } );
        }catch (Exception e){
            Toast.makeText( this, "Cadastro nao existe", Toast.LENGTH_SHORT ).show();
        }
    }

    public void click_regist(View view) {
        Intent signUp = new Intent( LoginActivity.this, SignUpActivity.class );
        startActivity( signUp );
        onStop();
    }
}
