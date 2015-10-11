package com.example.daniel.tarefa_i_3_a12danielgd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ActivitySecundaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_secundaria);

    }


    public void onPecharClick(View view) {
        EditText telf=(EditText) findViewById(R.id.telefono);
        EditText texto=(EditText) findViewById(R.id.cadBusqueda);
        Intent intent=new Intent();
        intent.putExtra("TEXTO",texto.getText().toString());
        intent.putExtra("TELF",telf.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }


}
