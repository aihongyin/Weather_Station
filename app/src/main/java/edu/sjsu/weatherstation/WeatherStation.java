package edu.sjsu.weatherstation;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;




public class WeatherStation extends Activity {

    private SensorManager sensorManager;
    private TextView temperatureTextView;
    private TextView pressureTextView;
    private TextView lightTextView;

    private Sensor temperatureSensor;
    private Sensor lightSensor;
    private Sensor pressureSensor;

    private float currentTemperature = Float.NaN;
    private float currentPressure = Float.NaN;
    private float currentLight = Float.NaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        temperatureTextView = (TextView) findViewById(R.id.temperature);
        pressureTextView = (TextView) findViewById(R.id.pressure);
        lightTextView = (TextView) findViewById(R.id.light);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);


        Timer updateTimer=new Timer("weatherUpdate");
        updateTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                updateGUI();
            }
        }, 0, 1000);
    }


    //This is for the temperature
    private final SensorEventListener tempSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            currentTemperature = event.values[0];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };

    //This is for the pressure
    private final SensorEventListener pressureSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            currentPressure = event.values[0];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };
    

    //This is for the Light
    private final SensorEventListener lightSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            currentLight = event.values[0];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

       // lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor != null) {
            sensorManager.registerListener(lightSensorEventListener,
                    lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            lightTextView.setText("Light Sensor Unavailable");
        }

        //pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (pressureSensor != null) {
            sensorManager.registerListener(pressureSensorEventListener,
                    pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            pressureTextView.setText("Barometer Unavailable");
        }

       // temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (temperatureSensor != null) {
            sensorManager.registerListener(tempSensorEventListener,
                    temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            temperatureTextView.setText("Thermometer Unavailable");
        }
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        sensorManager.unregisterListener(pressureSensorEventListener);
        sensorManager.unregisterListener(tempSensorEventListener);
        sensorManager.unregisterListener(lightSensorEventListener);
        super.onPause();
    }


    private void updateGUI() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if(!Float.isNaN(currentPressure)) {
                    pressureTextView.setText(currentPressure+"mBars");
                    pressureTextView.invalidate();
                }
                if (!Float.isNaN(currentLight)) {
                    String lightStr="Sunny";
                    if (currentLight<=SensorManager.LIGHT_CLOUDY) {
                        lightStr="Night";
                    } else if (currentLight<=SensorManager.LIGHT_OVERCAST) {
                        lightStr="Cloudy";
                    } else if (currentLight<=SensorManager.LIGHT_SUNLIGHT) {
                        lightStr="Overcast";
                    }
                    lightTextView.setText(lightStr);
                    lightTextView.invalidate();
                }
                if (!Float.isNaN(currentTemperature)) {
                    temperatureTextView.setText(currentTemperature+"C");
                    temperatureTextView.invalidate();
                }
            }
        });
    }
}

