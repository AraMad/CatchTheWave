package UsersInput;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Arina on 19.01.2017.
 */

public class LightHandler implements SensorEventListener {

    private float illumination;

    public LightHandler(Context context) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_LIGHT).size() != 0) {
            Sensor light_sensor = manager.getSensorList(Sensor.TYPE_LIGHT).get(0);
            manager.registerListener(this, light_sensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public LightHandler(SensorManager manager) {
        Sensor light_sensor = manager.getSensorList(Sensor.TYPE_LIGHT).get(0);
        manager.registerListener(this, light_sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        illumination = event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float getIllumination() {
        return illumination;
    }
}
