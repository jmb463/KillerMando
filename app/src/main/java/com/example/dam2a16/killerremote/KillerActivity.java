package com.example.dam2a16.killerremote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class KillerActivity extends AppCompatActivity implements ComunicaMenu{

    private EditText msg;
    private Button conect;
    private String sMsg;
    int port = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killer);

        msg = (EditText) findViewById(R.id.mensaje);
        conect = (Button) findViewById(R.id.conect);


        conect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sMsg = msg.getText().toString();
                boolean comprobacion = comprobar(sMsg);

                if (comprobacion) {
                    Toast.makeText(KillerActivity.this, "Nuevo jugador: " + sMsg, Toast.LENGTH_SHORT).show();

                    Intent intento = new Intent(KillerActivity.this, MenuActivity.class);
                    startActivity(intento);

                }
            }
        });


    }

    @Override
    public void menu(int queboton) {
        Intent in = new Intent(this, ToolActivity.class);

        in.putExtra("BOTONPULSADO", queboton);

        startActivity(in);
    }


    private boolean comprobar(String msg) {
        boolean vacio = true;
        if(msg.equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor escriba un mensaje", Toast.LENGTH_SHORT).show();
            vacio = false;

        }

        return vacio;
    }

    public String getNombre(){
        return this.sMsg;
    }
}
