package UsersInput;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Arina on 12.01.2017.
 */

public class AccelerometerHandler implements SensorEventListener {

    private float accel_X;
    private float accel_Y;
    private float accel_Z;

    public AccelerometerHandler(Context context) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public AccelerometerHandler(SensorManager manager) {
        Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accel_X = event.values[0];
        accel_Y = event.values[1];
        accel_Z = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public float getAccel_X() {
        return accel_X;
    }

    public float getAccel_Y() {
        return accel_Y;
    }

    public float getAccel_Z() {
        return accel_Z;
    }
}
