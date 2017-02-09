package UsersInput;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by Arina on 12.01.2017.
 */

public class ProximitySensorHandler implements SensorEventListener {

    private float sensor_state;

    public ProximitySensorHandler(Context context) {
        sensor_state = -1;
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_PROXIMITY).size() != 0) {
            Sensor proximity = manager.getSensorList(Sensor.TYPE_PROXIMITY).get(0);
            manager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public ProximitySensorHandler(SensorManager manager) {
        Sensor proximity = manager.getSensorList(Sensor.TYPE_PROXIMITY).get(0);
        manager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        sensor_state = event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public boolean isObjecNearDevice(){
        return (sensor_state == 0);
    }
}
