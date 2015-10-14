package com.example.daniel.tarefa_i_3_a12danielgd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPrincipal extends AppCompatActivity {
    private static final int COD_ACTIVITY_SECUNDARIA = 1;
    private String gardarCadena = "";
    private String gardarTelf = "";
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_principal);
        Button buscador = (Button) findViewById(R.id.buscador);


        final Intent intent = new Intent(this, ActivitySecundaria.class);
        buscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, COD_ACTIVITY_SECUNDARIA);
            }
        });

        buscador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(1);
                return true;
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Atención");
        dialog.setMessage("Que queres facer?");
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.setPositiveButton("Marcar Telefono", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!gardarTelf.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + gardarTelf));
                    try {
                        startActivity(intent);
                    }catch(SecurityException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(ActivityPrincipal.this, "Non hai ningun numero para mostrar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton("Buscar Cadena", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                if (!gardarCadena.equals("")) {
                    intent.putExtra(SearchManager.QUERY, gardarCadena);
                } else {
                    intent.putExtra(SearchManager.QUERY, "Casa");
                }
                startActivity(intent);
            }
        });
        return dialog.create();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_ACTIVITY_SECUNDARIA) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra("TEXTO")) {
                    gardarCadena = data.getExtras().getString("TEXTO");
                }
                if (data.hasExtra("TELF")) {
                    gardarTelf = data.getExtras().getString("TELF");
                }

            }
        }

    }


    public void mostrarToastDatos(View view) {
        Toast.makeText(this, "Busqueda: " + gardarCadena + "\n" + "Telefono: " + gardarTelf, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle estado) {
        estado.putString("TEXTO", gardarCadena);
        estado.putString("TELF", gardarTelf);
        super.onSaveInstanceState(estado);
        this.removeDialog(1);

    }

    @Override
    protected void onRestoreInstanceState(Bundle estado) {
        super.onRestoreInstanceState(estado);
        gardarCadena = estado.getString("TEXTO");
        gardarTelf = estado.getString("TELF");
    }
}
