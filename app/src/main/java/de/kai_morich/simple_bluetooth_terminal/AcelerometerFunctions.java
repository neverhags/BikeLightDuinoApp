package de.kai_morich.simple_bluetooth_terminal;

import android.util.Log;

public class AcelerometerFunctions {

    public void watcher(Float sensorEvent, Float sensorBreak) {
        Log.d("STATE X",sensorEvent + "");
        Log.d("STATE Z",sensorBreak + "");
        // Breaks
        if( sensorBreak > 2 ) {
            breaksOn();
            return;
        }

        if( sensorEvent < -2 ) {
            turnRight();
        }
        if( sensorEvent > 2 ) {
            turnLeft();
        }
        if( sensorEvent < 2 && sensorEvent > -2 ) {
            hold();
        }
    }
    public void turnLeft() {
        send("L");
    }
    public void turnRight() {
        send("R");
    }
    public void hold() {
        send("H");
    }
    public void breaksOn() {
        send("A");
    }

    private void send(String str) {
        TerminalFragment TF = new TerminalFragment();
        if(TF.isConnected() != TerminalFragment.Connected.True) {
            return;
        }
        Log.d("Connected",TF.isConnected().toString());
        try {
            byte[] data = (str).getBytes();
            TerminalFragment.socket.write(data);
        } catch (Exception e) {
            Log.d("Error when write data: ",TF.isConnected().toString());
        }
    }
}
