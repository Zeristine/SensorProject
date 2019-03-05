package hieubt.projects.presentation_sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class HumidityActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor humiditySensor;
    private SensorEventListener humiditySensorEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        if(humiditySensor==null){
            Toast.makeText(this, "No Humidity Sensor available", Toast.LENGTH_SHORT).show();
            finish();
        }

        humiditySensorEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                getSupportActionBar().setTitle("Humidity in % : " + event.values[0] + "%");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(humiditySensorEvent, humiditySensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(humiditySensorEvent);
    }
}
