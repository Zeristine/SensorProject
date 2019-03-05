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
    private SensorEventListener pressureSensorEvent = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            txt.setText(String.format("%.3f mbar",values[0]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);
        txt = findViewById(R.id.txt);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if(pressureSensor==null){
            Toast.makeText(this, "No Pressure Sensor available", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(pressureSensorEvent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(pressureSensorEvent, pressureSensor, SensorManager.SENSOR_DELAY_UI);
    }
}
