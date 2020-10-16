package com.example.wonkapeerb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnMessageLister{
    UDPConnection udp;
    private Button perro, sand, helado, papas;
    int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        udp = new UDPConnection();
        udp.start();
        udp.setObserver(this);
        perro = findViewById(R.id.perro);
        papas = findViewById(R.id.papas);
        sand = findViewById(R.id.sand);
        helado = findViewById(R.id.helado);

        perro.setOnClickListener(
                (view)->{
                    contador ++;

                    Date actual = new Date();
                    Pedido pedi = new Pedido("perro", contador, actual);
                    Gson gson = new Gson();
                    String json = gson.toJson(pedi);

                        udp.sendMessage(json);

                }
        );
        papas.setOnClickListener(
                (view)->{
                    contador ++;

                    Date actual = new Date();
                    Pedido pedi = new Pedido("papas", contador, actual);
                    Gson gson = new Gson();
                    String json = gson.toJson(pedi);

                    udp.sendMessage(json);

                }
        );

        sand.setOnClickListener(
                (view)->{
                    contador ++;

                    Date actual = new Date();
                    Pedido pedi = new Pedido("sandwich", contador, actual);
                    Gson gson = new Gson();
                    String json = gson.toJson(pedi);

                    udp.sendMessage(json);

                }
        );
        helado.setOnClickListener(
                (view)->{
                    contador ++;

                    Date actual = new Date();
                    Pedido pedi = new Pedido("helado", contador, actual);
                    Gson gson = new Gson();
                    String json = gson.toJson(pedi);

                    udp.sendMessage(json);

                }
        );

    }
    @Override
    public void onPedidoRecived(Pedido pos) {
        runOnUiThread(
                ()->{
                    Log.e("abcdefg","recibido"+ pos);
                    Toast.makeText(this, pos.getNombre()+" esta listooo", Toast.LENGTH_SHORT).show();
                }
        );

    }
}