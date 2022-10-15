package com.example.shakev2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mySensorManager;
    Sensor accl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);



        if(mySensorManager != null){
            accl = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


            if(accl != null){
                mySensorManager.registerListener(this, accl, mySensorManager.SENSOR_DELAY_NORMAL);


            }

        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT);
        }


        Button callFragment = (Button) findViewById(R.id.switchFragment);
        callFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, new BlankFragment()).commit();
                FrameLayout fl = (FrameLayout) findViewById(R.id.container);
                fl.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onSensorChanged(SensorEvent event){

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            TextView t1 = (TextView) findViewById(R.id.textView7);
            TextView t2 = (TextView) findViewById(R.id.textView8);
            TextView t3 = (TextView) findViewById(R.id.textView9);

            t1.setText("X: " + event.values[0]);
            t2.setText("Y: " + event.values[1]);
            t3.setText("Z: " + event.values[2]);
            Log.i("my","MainActivity");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    /*
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button2){
            getSupportFragmentManager().beginTransaction().replace(R.id.blankFragment,new Fragment()).commit();
        }
    }

     */
}

