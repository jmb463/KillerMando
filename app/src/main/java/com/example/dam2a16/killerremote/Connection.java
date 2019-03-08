package com.example.dam2a16.killerremote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection extends AppCompatActivity {

    private EditText ip,puerto,msg;
    private Button conect;
    private Socket cliente;
    private PrintWriter pw;
    private String sMsg,sPuerto,sIp;
    int port = 0;
/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        ip = (EditText) findViewById(R.id.dirip);
        puerto = (EditText) findViewById(R.id.puerto);
        msg = (EditText) findViewById(R.id.mensaje);
        conect = (Button) findViewById(R.id.conect);


        conect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conectar();
                sMsg = msg.getText().toString();
                sIp = ip.getText().toString();
                sPuerto = puerto.getText().toString();
                boolean comprobacion = comprobar(sMsg, sPuerto, sIp);

                if (comprobacion) {
                    Toast.makeText(Connection.this, "Conectando...", Toast.LENGTH_LONG).show();
                    port = Integer.parseInt(sPuerto);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                cliente = new Socket(ip.getText().toString(), port);
                                pw = new PrintWriter(cliente.getOutputStream());
                                pw.write(sMsg);
                                pw.flush();
                                pw.close();
                                cliente.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });

    }

    private void conectar(){

    }

    private boolean comprobar(String msg, String puerto, String ip) {
        boolean vacio = true;
        if(msg.equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor escriba un mensaje", Toast.LENGTH_SHORT).show();
            vacio = false;

        }
        if(msg.equalsIgnoreCase("start")){

        }
        if(puerto.equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor, escriba el puerto deseado", Toast.LENGTH_SHORT).show();
            vacio = false;
        }
        if(ip.equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor, escriba la ip deseada", Toast.LENGTH_SHORT).show();
            vacio = false;
        }
        return vacio;
    }*/
}
