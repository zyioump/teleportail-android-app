package wtf.lph.teleportail;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by simon on 04/09/16.
 */
public class ConnectThread extends Thread implements Runnable{
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private String msg = MainActivity.getIdManager().getId()+"o.";

    public ConnectThread(BluetoothDevice device){
        this.device = device;
    }
    public void run(){
        try{
            socket = device.createInsecureRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
            socket.connect();
            //MainActivity.getLogger().newLine("Connecté !!");

            outputStream = socket.getOutputStream();

            outputStream.write(msg.getBytes());
            //MainActivity.getLogger().newLine("Message envoyé !");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cancel();



        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void cancel(){
        try{
            socket.close();
            //MainActivity.getLogger().clear();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
