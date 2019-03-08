package com.example.dam2a16.killerremote;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class MenuFragment extends Fragment {

    private final int BOTONESMENU[] = {R.id.linterna,R.id.musica,R.id.nivel};

    private final int BOTONESILUMINADOS[] = {R.drawable.medallab,R.drawable.interrogacionb,R.drawable.herramientasb};

    private int boton;


    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mimenu = inflater.inflate(R.layout.fragment_menu, container, false); //Devuelve el fragmento que queremos cargar

        boton = -1;

        if(getArguments()!=null){
            boton = getArguments().getInt("BOTONPULSADO"); //Recuperar los datos del bundle
        }


        ImageButton botonmenu;

        for(int i = 0; i<BOTONESMENU.length;i++){

            botonmenu = (ImageButton) mimenu.findViewById(BOTONESMENU[i]);

            if(boton==i){
                botonmenu.setImageResource(BOTONESILUMINADOS[i]);
            }

            final int queBoton = i;

            botonmenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    Activity estaActividad = getActivity();

                    ((ComunicaMenu)estaActividad).menu(queBoton);

                }
            });
        }
        return mimenu;
    }

}
