package hieubt.projects.presentation_sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerSensorEvent;
    private TextView currentX, currentY, currentZ, maxX, maxY, maxZ;
    private float deltaX = 0, deltaY = 0, deltaZ = 0;
    private float deltaMaxX = 0, deltaMaxY = 0, deltaMaxZ = 0;
    private float prevX = 0, prevY = 0, prevZ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        currentX = findViewById(R.id.txtCurX);
        currentY = findViewById(R.id.txtCurY);
        currentZ = findViewById(R.id.txtCurZ);
        maxX = findViewById(R.id.txtMaxX);
        maxY = findViewById(R.id.txtMaxY);
        maxZ = findViewById(R.id.txtMaxZ);

        if (accelerometerSensor == null) {
            Toast.makeText(this, "No Accelerometer Sensor available", Toast.LENGTH_SHORT).show();
            finish();
        }

        accelerometerSensorEvent = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                displayCleanValues();
                displayCurrentValue();
                displayMaxValue();

                deltaX = Math.abs(prevX - event.values[0]);
                deltaY = Math.abs(prevY - event.values[1]);
                deltaZ = Math.abs(prevZ - event.values[2]);

                prevX = event.values[0];
                prevY = event.values[1];
                prevZ = event.values[2];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(accelerometerSensorEvent, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometerSensorEvent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accelerometerSensorEvent, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void displayCleanValues() {
        currentX.setText("Current X = " + 0);
        currentY.setText("Current Y = " + 0);
        currentZ.setText("Current Z = " + 0);
    }

    private void displayMaxValue() {
        if (deltaX > deltaMaxX) {
            deltaMaxX = deltaX;
            maxX.setText("Max X = " + deltaMaxX);
        }
        if (deltaY > deltaMaxY) {
            deltaMaxY = deltaY;
            maxY.setText("Max Y = " + deltaMaxY);
        }
        if (deltaZ > deltaMaxZ) {
            deltaMaxZ = deltaZ;
            maxZ.setText("Max Z = " + deltaMaxZ);
        }
    }

    private void displayCurrentValue() {
        currentX.setText("Current X = " + deltaX);
        currentY.setText("Current Y = " + deltaY);
        currentZ.setText("Current Z = " + deltaZ);
    }
}
