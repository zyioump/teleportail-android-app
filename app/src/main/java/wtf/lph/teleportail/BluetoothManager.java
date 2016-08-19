package wtf.lph.teleportail;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by simon on 15/08/16.
 */
public class BluetoothManager {
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket socket;
    private OutputStream    outputStream;
    private boolean connected;

    public BluetoothManager(){
        this.bluetoothAdapter = MainActivity.getBluetoothAdapter();
    }

    public BluetoothDevice getPortail(String nomPortail){
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();       //on récupère les appareil déjà appairer
        Iterator<BluetoothDevice> iterator = pairedDevices.iterator();

        boolean exist = false;
        BluetoothDevice device = null;

        while (iterator.hasNext()){
            BluetoothDevice aDevice = iterator.next();
            if (aDevice.getName().equals(nomPortail)){                              //si le portail est déjà appairé
                device = aDevice;
            }
        }

        return device;
    }

    public boolean connectToADevice(BluetoothDevice device){
        try{
            socket = device.createInsecureRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
            socket.connect();

            MainActivity.getLogger().newLine("Connecté !");
            connected = true;
            return true;
        } catch (IOException e){
            e.printStackTrace();

            MainActivity.getLogger().newLine("Pas connecté !");
            return false;
        }
    }

    public void sendMessage(String msg){
        try {
            outputStream = socket.getOutputStream();

            outputStream.write(msg.getBytes());
            outputStream.flush();

            MainActivity.getLogger().newLine("Message envoyé "+msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void cancelConnection(){
        try {
            outputStream.close();
            socket.close();
            connected = false;

            MainActivity.getLogger().newLine("Connexion achevée");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
