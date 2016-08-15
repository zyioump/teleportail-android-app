package wtf.lph.teleportail;

import android.widget.TextView;

/**
 * Created by simon on 15/08/16.
 */
public class Logger {
    private TextView log;
    private String textLog;

    public Logger(TextView log){
        this.log = log;
        textLog = "";
        this.log.setText(textLog);
    }

    public String newLine(String line){
        textLog += line+"\n";
        log.setText(textLog);

        return textLog;
    }

    public void clear(){
        textLog = "";
        log.setText(textLog);
    }
}
