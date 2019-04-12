package com.navegam.fepsfretefirebase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.navegam.cpfvalidador.Mask;
import com.navegam.fepsfretefirebase.R;
import com.navegam.fepsfretefirebase.Utils.NavegamData;

public class EmployeeRegistrationActivity extends AppCompatActivity {

    private EditText edt_nameEmployee,edt_CPFEmployee, edt_emailEmployee,
            edt_loginEmployee, edt_passwordEmployee;

    private Button btn_registerEmployee;

    private DatabaseReference databaseReferenceFunc;

    SharedPreferences sharedPreferences;
    NavegamData navegamDataEmployee = new NavegamData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_employee_registration );

        edt_nameEmployee = findViewById( R.id.edt_nameEmployee );
        edt_emailEmployee = findViewById( R.id.edt_emailEmployee );
        edt_CPFEmployee = findViewById( R.id.edt_CPFEmployee );
        edt_loginEmployee = findViewById( R.id.edt_loginEmployee );
        edt_passwordEmployee = findViewById( R.id.edt_passwordEmployee );

        btn_registerEmployee = findViewById( R.id.btn_registerEmployee );

        databaseReferenceFunc = FirebaseDatabase.getInstance().getReference().child( "Navegam" );

        sharedPreferences = getSharedPreferences( "Navegam",MODE_PRIVATE );

        edt_CPFEmployee.addTextChangedListener( Mask.Companion.mask("###.###.###-##", edt_CPFEmployee));

        btn_registerEmployee.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterEmployee();
            }
        } );

    }

    private void RegisterEmployee() {
        navegamDataEmployee.setLogin( edt_loginEmployee.getText().toString().toUpperCase() );
        navegamDataEmployee.setPassword( edt_passwordEmployee.getText().toString() );
        navegamDataEmployee.setCPF( edt_CPFEmployee.getText().toString() );
        navegamDataEmployee.setEmail( edt_emailEmployee.getText().toString() );

        navegamDataEmployee.setAdminOwner( "" );
        navegamDataEmployee.setEmployees( "funcionario" );

        String boatFunc = sharedPreferences.getString( "boatFunc","" );
        navegamDataEmployee.setNameBoat( boatFunc );

        databaseReferenceFunc.child( navegamDataEmployee.getLogin() ).setValue( navegamDataEmployee ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( EmployeeRegistrationActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( EmployeeRegistrationActivity.this, "Falha ao cadastrar", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
}
