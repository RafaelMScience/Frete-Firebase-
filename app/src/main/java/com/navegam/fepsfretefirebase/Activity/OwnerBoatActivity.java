package com.navegam.fepsfretefirebase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navegam.fepsfretefirebase.R;
import com.navegam.fepsfretefirebase.Utils.NavegamData;

public class OwnerBoatActivity extends AppCompatActivity implements EmployeeOwnerDialog.DialogListerner {


    private DatabaseReference databaseReferenceOwner;

    NavegamData navegamData = new NavegamData();

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_owner_boat );

        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference().child( "Navegam" );

        sharedPreferences = getSharedPreferences( "Navegam",MODE_PRIVATE );
    }

    public void Register_Funcionario(View view) {
        EmployeeOwnerDialog employee = new EmployeeOwnerDialog();
        employee.show( getSupportFragmentManager(),"Funcionario" );
    }


    @Override
    public void applyTextes(String username, String password) {
        navegamData.setLogin( username.trim().toUpperCase() );
        navegamData.setPassword( password.trim() );
        navegamData.setEmployees( "funcionario" );

        navegamData.setAdminOwner( "" );
        navegamData.setCPF( "" );

        String boatFunc = sharedPreferences.getString( "boatFunc","" );
        navegamData.setNameBoat( boatFunc );

        databaseReferenceOwner.child( navegamData.getLogin() ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText( OwnerBoatActivity.this, "nome ja existe", Toast.LENGTH_SHORT ).show();

                }else {
                    databaseReferenceOwner.child( navegamData.getLogin() ).setValue( navegamData ).addOnCompleteListener( new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText( OwnerBoatActivity.this, "Funcionario permitido", Toast.LENGTH_SHORT ).show();
                            } else {
                                Toast.makeText( OwnerBoatActivity.this, "Falha ao permitir", Toast.LENGTH_SHORT ).show();
                            }
                        }
                    } );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }
}
