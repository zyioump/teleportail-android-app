package wtf.lph.teleportail;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.*;

/**
 * Created by simon on 15/08/16.
 */
public class Listener {
    private Button connectDisconnect;
    private BluetoothAdapter bluetoothAdapter;
    private TextView portailNom;

    public Listener(final Button connectDisconnect,final TextView portailNom){
        this.connectDisconnect = connectDisconnect;
        this.bluetoothAdapter  = MainActivity.getBluetoothAdapter();
        this.portailNom = portailNom;

        connectDisconnect.setOnClickListener(new View.OnClickListener() {               //Quand on clique sur le bouton connect disconnect
            @Override
            public void onClick(View view) {
                MainActivity.getLogger().clear();

                if (bluetoothAdapter == null)
                    MainActivity.getLogger().newLine("Votre Téléphone n'est pas équipé du Bluetooth");      //Si il y a pas de BT
                else {                                                                                                       //Si il y en a
                    MainActivity.getLogger().newLine("Portail : " + portailNom.getText().toString());
                    BluetoothDevice device = MainActivity.getBluetoothManager().getPortail(portailNom.getText().toString());

                    if (device != null) { //portail déjà appairé
                        MainActivity.getLogger().newLine("Le portail est déjà appairé");

                        /*if (MainActivity.getBluetoothManager().connectToADevice(device) == true) {                            //On essai de se connecté
                            MainActivity.getBluetoothManager().sendMessage(MainActivity.getIdManager().getId()+"o.\n");            //Si on réussi
                            MainActivity.getBluetoothManager().cancelConnection();
                        }*/

                        Thread connectThread = new ConnectThread(device);
                        connectThread.start();

                    } else {
                        MainActivity.getLogger().newLine("Le portail n'est pas encore appairé");
                    }
                }

            }
        });

        /*openClose.setOnClickListener(new View.OnClickListener() {                       //Quand on clique sur le bouton open close
            @Override
            public void onClick(View view) {
                if(MainActivity.getBluetoothManager().getConnected() == true){
                    MainActivity.getBluetoothManager().sendMessage(MainActivity.getIdManager().getId()+"o.");
                }else{
                    MainActivity.getLogger().newLine("Vous devez d'abord vous connecté");
                }
            }
        });*/
    }
}
