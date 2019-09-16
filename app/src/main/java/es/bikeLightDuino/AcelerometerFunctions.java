package es.bikeLightDuino;

import android.util.Log;

public class AcelerometerFunctions {
    private TerminalFragment TF = new TerminalFragment();

    public void watcher(Float sensorX, Float sensorY, Float sensorZ) {
        Integer lrRolerance = 1;
        Integer brTolerance = 1;
        try {
            lrRolerance = TF.getLRTolerance();
            brTolerance = TF.getBRTolerance();
        } catch(Exception e) {}

        Log.d("STATE X",sensorX + "");
        Log.d("STATE Y",sensorY + "");
        Log.d("STATE Z",sensorZ + "");
        // Breaks
        if( sensorZ > brTolerance && sensorY < (9.8-brTolerance) ) {
            breaksOn();
            return;
        }

        if( sensorX < -lrRolerance ) {
            turnRight();
        }
        if( sensorX > lrRolerance ) {
            turnLeft();
        }
        if( sensorX < lrRolerance && sensorX > -lrRolerance ) {
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
