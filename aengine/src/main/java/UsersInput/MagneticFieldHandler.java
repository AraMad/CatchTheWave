package UsersInput;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static java.lang.Math.abs;

/**
 * Created by Arina on 19.01.2017.
 */

public class MagneticFieldHandler implements SensorEventListener {

    private float mag_X;
    private float mag_Y;
    private float mag_Z;

    public MagneticFieldHandler(Context context) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).size() != 0) {
            Sensor magnetic_field = manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0);
            manager.registerListener(this, magnetic_field, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public MagneticFieldHandler(SensorManager manager) {
        Sensor magnetic_field = manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0);
        manager.registerListener(this, magnetic_field, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mag_X = event.values[0];
        mag_Y = event.values[1];
        mag_Z = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float getMag_Z() {
        return mag_Z;
    }

    public float getMag_X() {
        return mag_X;
    }

    public float getMag_Y() {
        return mag_Y;
    }

    public float getMagneticField(){
        return abs(mag_X+mag_Y+mag_Z);
    }
}
