package wtf.lph.teleportail;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*private static String nomBT;
    private Button connect;
    private static TextView log;
    private static String textLog = "";
    private BluetoothDevice portailBT;*/

    private static Logger log;
    private static BluetoothManager bluetoothManager;
    private static BluetoothAdapter bluetoothAdapter;
    private Listener listener;

    public static Logger getLogger() {return log;}
    public static BluetoothAdapter getBluetoothAdapter() {return  bluetoothAdapter;}
    public static BluetoothManager getBluetoothManager() {return bluetoothManager;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //On dit la layout a utiliser au démarage de l'app

        log = new Logger((TextView) findViewById(R.id.log));    //Démarrage des log
        log.clear();


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bluetoothManager = new BluetoothManager();

        listener = new Listener((Button) findViewById(R.id.bCo), (Button) findViewById(R.id.bO), (TextView) findViewById(R.id.nomBT));   //Démarrage des listeners



        /*log = (TextView) findViewById(R.id.log);

        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); //on récupère l'adaptateur BT

        if (bluetoothAdapter == null)   //si il y a pas de BT
            Toast.makeText(MainActivity.this, "Votre appareil n'a pas de bluetooth",
                    Toast.LENGTH_SHORT).show();
        else{
            //quand il y a du bluetooth
            if (!bluetoothAdapter.isEnabled()) {    //on allume le BT
                bluetoothAdapter.enable();
            }

            connect = (Button) findViewById(R.id.bCo);

            connect.setOnClickListener(new View.OnClickListener() { //quand le boutton open est presser
                @Override
                public void onClick(View view) {
                    textLog = "";
                    boolean appair = false;

                    EditText nomBTET = (EditText) findViewById(R.id.nomBT);
                    nomBT = nomBTET.getText().toString();

                    textLog = textLog + "Portail : " + nomBT;
                    log.setText(textLog);

                    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();       //on récupère les appareil déjà appairer
                    Iterator<BluetoothDevice> iterator = pairedDevices.iterator();

                    while (iterator.hasNext()){
                        BluetoothDevice BTDevice = iterator.next();
                        if (BTDevice.getName().equals(nomBT)){                              //si le portail est déjà appairé
                            textLog = textLog + "\n"+nomBT+" est déjà appairé";
                            log.setText(textLog);
                            appair = true;

                            portailBT = BTDevice;
                        }
                    }

                    if (appair != true){                    //si le portail n'est pas appairé
                        textLog = textLog + "\n"+nomBT+" n'est pas appairé";
                        log.setText(textLog);



                        textLog = textLog + "\nMerci de l'appairé";
                        log.setText(textLog);
                    }else{
                        try {
                            BluetoothSocket socket = portailBT.createRfcommSocketToServiceRecord(portailBT.getUuids()[0].getUuid());
                            socket.connect();
                            MainActivity.textLog = textLog + "\nConnecté !";
                            log.setText(textLog);
                        }catch (IOException e ){
                            e.printStackTrace();
                            textLog = textLog + "\nImpossible de se connecté";
                            log.setText(textLog);
                        }

                    }
                }
            });
        }*/
    }
}
