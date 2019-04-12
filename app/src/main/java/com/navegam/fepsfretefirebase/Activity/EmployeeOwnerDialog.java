package com.navegam.fepsfretefirebase.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.navegam.fepsfretefirebase.R;

import java.util.Objects;

import androidx.appcompat.app.AppCompatDialogFragment;

public class EmployeeOwnerDialog extends AppCompatDialogFragment {

    private EditText edt_funcionario;
    private EditText edt_passwordFuncionario;

    private DialogListerner listerner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );

        LayoutInflater inflater = Objects.requireNonNull( getActivity() ).getLayoutInflater();
        View view = inflater.inflate( R.layout.layout_dialog_employee_registration,null );

        builder.setView( view )
                .setTitle( "Login Funcionario" )
                .setNegativeButton( "Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                } )
                .setPositiveButton( "Criar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String username = edt_funcionario.getText().toString();
                        String password = edt_passwordFuncionario.getText().toString();
                        listerner.applyTextes( username,password );
                    }
                } );

        edt_funcionario = view.findViewById( R.id.edt_loginFuncionario );
        edt_passwordFuncionario = view.findViewById( R.id.edt_PasswordFuncionario );

        return  builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );

        try {
            listerner =  (DialogListerner) context;
        } catch (ClassCastException e){
            throw new ClassCastException( context.toString() +
                    "must Implement Example");
        }
    }

    public interface DialogListerner{
        void applyTextes(String username, String password);
    }
}
