package com.example.dam2a16.killerremote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private EditText editText_ip;
    private EditText editText_port;
    private ConnectServer cs;
    private KillerActivity ka;
    public static final String CONNECTION_PREFERENCES = "ConPrefs";
    private Spinner spinner;
    private Ranking rank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        this.editText_ip = (EditText) findViewById(R.id.editText_ip);
        this.editText_port = (EditText) findViewById(R.id.editText_port);


        this.cs = new ConnectServer();

        SharedPreferences settings = getSharedPreferences(CONNECTION_PREFERENCES, MODE_PRIVATE);


        String ip = settings.getString("IP", "172.16.203.5");
        String port = settings.getString("Port", "8000");
        //String nombre = settings.getString("Nombre", this.ka.getNombre());
        Log.i("myconn", "Last ip " + ip + " \tLast port " + port);
        this.editText_ip.setText(ip);
        this.editText_port.setText(port);

        this.spinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.brew_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        this.spinner.setAdapter(staticAdapter);

    }


    public void connect(View v) {
        Toast.makeText(this, "Connecting...", Toast.LENGTH_SHORT).show();
        this.cs.setHOST(this.editText_ip.getText().toString());
        this.cs.setPORT(Integer.parseInt(this.editText_port.getText().toString()));
        if ( this.cs.makeContact()){
            this.cs.sendCommand("mtest");
            this.savePreferences();
            this.start();
        }else{
            Toast toast1 = Toast.makeText(getApplicationContext(), "IP o puerto no encontrado", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    public void start(){
        String color = spinner.getSelectedItem().toString();
        //String name = this.ka.getNombre();
        Intent intent = new Intent(this, MainActivity.class);
        String connection = this.cs.getHOST() + "&" + this.cs.getPORT() + "&" +color;
        intent.putExtra("Connection", connection);
        startActivity(intent);
    }

    /*public void saltar(){
        Intent intento = new Intent(this,MenuActivity.class);
        startActivity(intento);
    }*/

    private void savePreferences(){
        String ip = String.valueOf(this.editText_ip.getText());
        String port = String.valueOf(this.editText_port.getText());
        //String name = this.ka.getNombre();

        Log.i("myconn", "Destruido " + ip + " " + port);
        SharedPreferences settings = getSharedPreferences(CONNECTION_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.putString("IP", ip);
        prefEditor.putString("port", port);
        //prefEditor.putString("name",name);
        prefEditor.commit();
    }


}