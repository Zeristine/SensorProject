package hieubt.projects.presentation_sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ThermometerActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor thermometerSensor;
    private SensorEventListener thermometerSensorEvent;
    private float temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        thermometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (thermometerSensor == null) {
            Toast.makeText(this, "There is no Ambient Temperature sensor", Toast.LENGTH_SHORT).show();
            finish();
        }

        thermometerSensorEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                temperature = event.values[0];
                getSupportActionBar().setTitle("Temperature : " + temperature + " C");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(thermometerSensorEvent, thermometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(thermometerSensorEvent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(thermometerSensorEvent, thermometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

}
