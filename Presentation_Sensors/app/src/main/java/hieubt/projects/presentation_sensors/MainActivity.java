package hieubt.projects.presentation_sensors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickToLumiDetect(View view) {
        intent = new Intent(this, LuminosityActivity.class);
        startActivity(intent);
    }

    public void clickToProxiDetect(View view) {
        intent = new Intent(this, ProximityActivity.class);
        startActivity(intent);
    }

    public void clickToThermometer(View view) {
        intent = new Intent(this, ThermometerActivity.class);
        startActivity(intent);
    }

    public void clickToShowSensors(View view) {
        intent = new Intent(this, SensorsListActivity.class);
        startActivity(intent);
    }

    public void clickToHumidity(View view) {
        intent = new Intent(this, HumidityActivity.class);
        startActivity(intent);
    }

    public void clickToPressure(View view) {
        intent = new Intent(this, PressureActivity.class);
        startActivity(intent);
    }

    public void clickToAccelerometer(View view) {
        intent = new Intent(this, AccelerometerActivity.class);
        startActivity(intent);
    }
}
