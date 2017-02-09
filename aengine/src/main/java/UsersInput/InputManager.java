package UsersInput;

import android.content.Context;

/**
 * Created by Arina on 12.01.2017.
 */

public class InputManager {

    private ScreenTouchHandler screenTouchHandler;
    private AccelerometerHandler accelerometerHandler;
    private ProximitySensorHandler proximitySensorHandler;

    public InputManager() {
        screenTouchHandler = null;
        accelerometerHandler = null;
        proximitySensorHandler = null;
    }

    public void turnOnScreenTouchListener(){
        screenTouchHandler = new ScreenTouchHandler();
    }

    public void turnOnAccelerometListener(Context context){
        accelerometerHandler = new AccelerometerHandler(context);
    }

    public void turnOnProximityListener(Context context){
        proximitySensorHandler = new ProximitySensorHandler(context);
    }

    public float[] getTouchCoordinates() {
        if (screenTouchHandler == null) {
            return null;
        }

        float[] coordinates = new float[2];
        coordinates[0] = screenTouchHandler.getCoordinateOfTouch_X();
        coordinates[1] = screenTouchHandler.getCoordinateOfTouch_Y();

        return coordinates;
    }

    public String getTypeOfTouch(){
        if (screenTouchHandler == null) {
            return null;
        }
        return  screenTouchHandler.getStateOfTouch();
    }

    public float getAccelerometrData() {
        if (accelerometerHandler == null) {
            return  0; //null;
        }

        float[] data = new float[3];
        data[0] = accelerometerHandler.getAccel_X();
        data[1] = accelerometerHandler.getAccel_Y();
        data[2] = accelerometerHandler.getAccel_Z();

        return accelerometerHandler.getAccel_X();
    }

    public boolean getStateOfObject(){
        if (proximitySensorHandler == null){
            return false;
        }
        return proximitySensorHandler.isObjecNearDevice();
    }
}
