package wtf.lph.teleportail;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Random;

/**
 * Created by simon on 17/08/16.
 */
public class IDManager {
    private Context context;
    private SharedPreferences preferences;
    private int id;

    public IDManager(Context context){
        this.context = context;

        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if(hasID() == false){
            newID();
        }else{
            MainActivity.getLogger().newLine("id : "+preferences.getInt("id", 0));
        }
    }

    private boolean hasID(){
        if (preferences.getInt("id", 0) == 0){
            MainActivity.getLogger().newLine("L'app n'as pas encore d'id");
            return false;
        }
        return true;
    }

    public void newID(){
        Random random = new Random();
        id = 100 + random.nextInt(999-100);

        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("id", id);

        editor.commit();

        MainActivity.getLogger().newLine("Nouvelle id : "+ preferences.getInt("id", 0));
    }

    public int getId() {return preferences.getInt("id", 0);}
}
