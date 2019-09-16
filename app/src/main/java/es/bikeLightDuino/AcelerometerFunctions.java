package es.bikeLightDuino;

import android.util.Log;

public class AcelerometerFunctions {
    private TerminalFragment TF = new TerminalFragment();

    public void watcher(Float sensorX, Float sensorY, Float sensorZ) {
        Integer tolerance = 0;
        try {
            tolerance = TF.getTolerance();
        } catch(Exception e) {}

        Log.d("STATE X",sensorX + "");
        Log.d("STATE Y",sensorY + "");
        Log.d("STATE Z",sensorZ + "");
        // Breaks
        if( sensorZ > tolerance && sensorY < (9.8-tolerance) ) {
            breaksOn();
            return;
        }

        if( sensorX < -tolerance ) {
            turnRight();
        }
        if( sensorX > tolerance ) {
            turnLeft();
        }
        if( sensorX < tolerance && sensorX > -tolerance ) {
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
