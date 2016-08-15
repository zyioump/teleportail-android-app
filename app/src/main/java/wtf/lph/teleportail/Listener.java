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
    private Button openClose;
    private BluetoothAdapter bluetoothAdapter;
    private TextView portailNom;
    private String modeCD = "connect";

    public Listener(final Button connectDisconnect, Button openClose, final TextView portailNom){
        this.connectDisconnect = connectDisconnect;
        this.openClose         = openClose;
        this.bluetoothAdapter  = MainActivity.getBluetoothAdapter();
        this.portailNom = portailNom;

        connectDisconnect.setOnClickListener(new View.OnClickListener() {               //Quand on clique sur le bouton connect disconnect
            @Override
            public void onClick(View view) {
                MainActivity.getLogger().clear();
                if(modeCD.equals("connect")) {
                    if (bluetoothAdapter == null)
                        MainActivity.getLogger().newLine("Votre Téléphone n'a pas de Bluetooth");      //Si il y a pas de BT
                    else {                                                                                                       //Si il y en a
                        MainActivity.getLogger().newLine("Portail : " + portailNom.getText().toString());
                        BluetoothDevice device = MainActivity.getBluetoothManager().getPortail(portailNom.getText().toString());

                        if (device != null) { //portail déjà appairé
                            MainActivity.getLogger().newLine("Le portail est appairés");

                            if (MainActivity.getBluetoothManager().connectToADevice(device) == true) {                            //On essai de se connecté
                                connectDisconnect.setText("SE DECONNECTE");                                                     //Si on réussi on affiche le bouton disconnect
                                modeCD = "disconnect";
                            }
                        } else {
                            MainActivity.getLogger().newLine("Le portail n'est pas appairés");
                        }
                    }
                }else{
                    MainActivity.getBluetoothManager().cancelConnection();
                    modeCD = "connect";
                    connectDisconnect.setText("SE CONNECTE");
                }
            }
        });

        openClose.setOnClickListener(new View.OnClickListener() {                       //Quand on clique sur le bouton open close
            @Override
            public void onClick(View view) {
                if(MainActivity.getBluetoothManager().getConnected() == true){
                    MainActivity.getBluetoothManager().sendMessage("2o.");
                }else{
                    MainActivity.getLogger().newLine("Vous devez d'abord vous connecté");
                }
            }
        });
    }
}
