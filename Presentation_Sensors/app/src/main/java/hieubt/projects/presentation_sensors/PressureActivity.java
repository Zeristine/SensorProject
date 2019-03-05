package hieubt.projects.presentation_sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PressureActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private SensorEventListener pressureSensorEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if(pressureSensor==null){
            Toast.makeText(this, "No Humidity Sensor available", Toast.LENGTH_SHORT).show();
            finish();
        }

        pressureSensorEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                getSupportActionBar().setTitle("Pressure Value : " + event.values[0] + "hPa");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(pressureSensorEvent, pressureSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(pressureSensorEvent);
    }

}
