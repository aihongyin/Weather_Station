
package edu.sjsu.weatherstation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/*

        public class MainActivity extends Activity {
            private SensorManager sensorManager;
            private Sensor mLight;
            private Sensor pressureSensor;
            private Sensor mySensor;
            private float myLight;
            private float myPressure;

            TextView light = findViewById(R.id.light);
            TextView pressure = findViewById(R.id.pressure);

            @Override
            public final void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

               // sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                SensorManager sm = (SensorManager)this.getSystemService(SENSOR_SERVICE);
                mLight = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
                sm.registerListener((SensorEventListener) this, mLight, SensorManager.SENSOR_DELAY_GAME);
                pressureSensor=sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
                sm.registerListener((SensorEventListener) this, pressureSensor, SensorManager.SENSOR_DELAY_GAME);




            }



            SensorEventListener sensorEventListener = new SensorEventListener() {
                @Override
                public final void onSensorChanged (SensorEvent event){

                    if(event.sensor.getType()==Sensor.TYPE_LIGHT) {

                        light.setText("The light is: " + event.values[0]);
                    }

                    if(event.sensor.getType()==Sensor.TYPE_PRESSURE) {
                        pressure.setText("The pressure is: " + event.values[0]);
                    }

                    // The light sensor returns a single value.
                    // Many sensors return 3 values, one for each axis.
                   // float lux = event.values[0];
                    // Do something with this sensor value.
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }

            };

            @Override
            protected void onResume() {
                super.onResume();
                // lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                if ( mLight!= null) {
                    sensorManager.registerListener((SensorEventListener) this, mLight, SensorManager.SENSOR_DELAY_GAME);

                } else {
                    light.setText("Light Sensor Unavailable");
                }

                //pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
                if (pressureSensor != null) {

                    sensorManager.registerListener((SensorEventListener) this, pressureSensor, SensorManager.SENSOR_DELAY_GAME);

                } else {
                    pressure.setText("Barometer Unavailable");
                }

            }



            @Override
            protected void onPause() {
                super.onPause();
                sensorManager.unregisterListener((SensorEventListener) this);
            }
        }


*/

public class MainActivity extends AppCompatActivity implements
        SensorEventListener{

private double light;
private double pressure;
TextView lightView;
TextView pressureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightView = findViewById(R.id.light);
        pressureView = findViewById(R.id.pressure);


        SensorManager sm = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        Sensor se = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener((SensorEventListener) this, se, SensorManager.SENSOR_DELAY_GAME);
       Sensor se1 = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
       sm.registerListener((SensorEventListener) this, se1, SensorManager.SENSOR_DELAY_GAME);
        Sensor se2 = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
       sm.registerListener((SensorEventListener) this, se2, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            //Update UI

        }
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            //UpdateUI
            lightView.setText("The light is: " + event.values[0]);
        }

        if(event.sensor.getType()==Sensor.TYPE_PRESSURE){
            //UpdateUI
             pressureView.setText("The pressure is: " + event.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

