package com.example.dam2a16.killerremote;


import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Server server;
    private RelativeLayout layout_joystick;
    private JoyStickController joystick;
    private MediaPlayer disparo;
    private Vibrator vibrate;
    private TextView textoNombre;
    private KillerActivity ka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textoNombre = (TextView) findViewById(R.id.text);
        this.disparo = MediaPlayer.create(this, R.raw.disparo);
        this.vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        String connection = getIntent().getExtras().getString("Connection");
        this.server = new Server();

        String ip = connection.substring(0, connection.indexOf("&"));
        connection = connection.substring(connection.indexOf("&") + 1);
        String port = connection.substring(0, connection.indexOf("&"));
        String color = connection.substring(connection.indexOf("&") + 1);
        String nombre = this.ka.getNombre();
        this.server.setHOST(ip);
        this.server.setPORT(Integer.parseInt(port));
        textoNombre.setText(nombre);

        if (this.server.makeContact()) {
            this.server.setMainActivity(this);
            new Thread(this.server).start();
            this.server.sendCommand("mcone");
            this.sendColor(color);

        } else {
            System.out.println("Error connection");
        }


        layout_joystick = (RelativeLayout) findViewById(R.id.layout_joystick);

        joystick = new JoyStickController(getApplicationContext(), layout_joystick, R.drawable.image_button, this.server);
        joystick.setStickSize(150, 150);
        joystick.setLayoutSize(500, 500);
        joystick.setLayoutAlpha(150);
        joystick.setStickAlpha(100);
        joystick.setOffset(90);
        joystick.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                String dir = "st";
                joystick.drawStick(arg1);
                if (arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    int direction = joystick.get8Direction();
                    if (direction == JoyStickController.STICK_UP) {
                        dir = "up";
                    } else if (direction == JoyStickController.STICK_UPRIGHT) {
                        dir = "ur";
                    } else if (direction == JoyStickController.STICK_RIGHT) {
                        dir = "ri";
                    } else if (direction == JoyStickController.STICK_DOWNRIGHT) {
                        dir = "dr";
                    } else if (direction == JoyStickController.STICK_DOWN) {
                        dir = "do";
                    } else if (direction == JoyStickController.STICK_DOWNLEFT) {
                        dir = "dl";
                    } else if (direction == JoyStickController.STICK_LEFT) {
                        dir = "le";
                    } else if (direction == JoyStickController.STICK_UPLEFT) {
                        dir = "ul";
                    } else if (direction == JoyStickController.STICK_NONE) {
                        dir = "st";
                    }
                } else if (arg1.getAction() == MotionEvent.ACTION_UP) {
                    dir = "st";
                }
                //Habra que hacer lo del command en connection

                joystick.getConnectionServer().sendMoveCommand(dir);

                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        this.server.closeLink();
        MainActivity.this.finish();
        super.onPause();
    }

    public void shoot(View v) {
        this.disparo.start();
        this.server.sendCommand("shoot");
    }

    public void die() {
        this.vibrate.vibrate(500);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textoNombre.setBackgroundColor(Color.RED);
                server.closeLink();
                MainActivity.this.finish();




            }
        });

    }

    private void sendColor(String color) {

        int red = 0;
        int blue = 0;
        int green = 0;
        switch (color) {
            case "Blue":
                blue = 255;
                break;
            case "Red":
                red = 255;
                break;
            case "Green":
                green = 255;
                break;
            case "Brown":
                red = 128;
                green = 64;
                break;
            default:

                break;
        }
        this.server.sendCommand("color" + red + "&" + green + "&" + blue);
    }

    public void setServer(Server connection) {
        this.server = connection;
    }


}

