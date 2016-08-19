package wtf.lph.teleportail.thread;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;

import wtf.lph.teleportail.MainActivity;

/**
 * Created by simon on 19/08/16.
 */
public class ConnectThread extends Thread implements Runnable {
    BluetoothSocket socket;
    OutputStream out;

    public ConnectThread(BluetoothDevice device){
        try{
            socket = device.createInsecureRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            socket.connect();
            MainActivity.getLogger().newLine("Connecté");


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
