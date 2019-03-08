package com.example.dam2a16.killerremote;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActividadHerramientas extends AppCompatActivity implements ComunicaMenu{

    Fragment[] misFragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_herramientas);

        misFragmentos = new Fragment[3];

        misFragmentos[0]=new Ranking();
        misFragmentos[1]=new Help();
        misFragmentos[2]=new Options();

        Bundle extras = getIntent().getExtras();

        menu(extras.getInt("BOTONPULSADO"));
    }

    @Override
    public void menu(int queboton) {

        FragmentManager miManejador = getFragmentManager();

        FragmentTransaction miTransaccion = miManejador.beginTransaction();

        Fragment menu_iluminado = new MenuFragment(); //Fragmento de tipo menu

        Bundle datos = new Bundle();

        datos.putInt("BOTONPULSADO", queboton); //Guardamos los datos en el bundle

        menu_iluminado.setArguments(datos); //Enviamos el bundle al menu

        miTransaccion.replace(R.id.menu,menu_iluminado);
        miTransaccion.replace(R.id.herramientas,misFragmentos[queboton] );
        miTransaccion.commit();
    }
}
