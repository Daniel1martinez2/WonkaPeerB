package com.example.wonkapeerb;


import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread {

    private DatagramSocket socket;
    private OnMessageLister observer;

    public void setObserver(OnMessageLister observer) {
        this.observer = observer;

    }
    public void run() {

        try {
            //1. escuchar
            socket = new DatagramSocket(6000);
            //2. esperar
            while(true) {

                byte [] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length );
                // congelado esperando
                System.out.println("esperando");
                socket.receive(packet);
                String msg = new String(packet.getData()).trim();
                Log.e("abcdefg","recibido"+ msg);
                Gson gson = new Gson();
                Pedido p = gson.fromJson(msg,Pedido.class);
                observer.onPedidoRecived(p);
            }
        }catch(SocketException e) {
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void sendMessage(String message) {
        new Thread(

                ()->{

                    try {
                        InetAddress ip = InetAddress.getByName("192.168.0.10");
                        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, 5000);
                        socket.send(packet);
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }


        ).start();


    }


}