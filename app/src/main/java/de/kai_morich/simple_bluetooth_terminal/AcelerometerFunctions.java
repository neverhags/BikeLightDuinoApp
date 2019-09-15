package de.kai_morich.simple_bluetooth_terminal;

import android.util.Log;

public class AcelerometerFunctions {

    public void watcher(Float sensorX, Float sensorY, Float sensorZ) {
        Log.d("STATE X",sensorX + "");
        Log.d("STATE Y",sensorY + "");
        Log.d("STATE Z",sensorZ + "");
        // Breaks
        if( sensorZ > 2 && sensorY < (9.8-2) ) {
            breaksOn();
            return;
        }

        if( sensorX < -2 ) {
            turnRight();
        }
        if( sensorX > 2 ) {
            turnLeft();
        }
        if( sensorX < 2 && sensorX > -2 ) {
            hold();
        }
    }
    private void turnLeft() {
        send("L");
    }
    private void turnRight() {
        send("R");
    }
    private void hold() {
        send("H");
    }
    private void breaksOn() {
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
            Log.d("Error when write data",TF.isConnected().toString());
        }
    }
}
