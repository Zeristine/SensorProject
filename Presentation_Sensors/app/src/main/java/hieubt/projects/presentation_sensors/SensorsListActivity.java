package hieubt.projects.presentation_sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SensorsListActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private LinearLayout sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_list);
        sensorList = findViewById(R.id.sensorList);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            createSensorDetailView(sensor);
        }
    }

    private void createSensorDetailView(Sensor sensor) {
        TextView txtName = new TextView(this);

        txtName.setText(sensor.getName());
        String content = "- Int Type : " + sensor.getType() + "\n"
                + "- Vendor : " + sensor.getVendor() + "\n"
                + "- Version : " + sensor.getVersion() + "\n"
                + "- Resolution : " + sensor.getResolution() + "\n"
                + "- Power : " + sensor.getPower() + "\n"
                + "- Max Range : " + sensor.getMaximumRange();
        TextView txtContent = new TextView(this);
        txtContent.setText(content);

        sensorList.addView(txtName);
        sensorList.addView(txtContent);
    }

}
