package com.anuj.android.app.accelerometergolf;

import android.os.Bundle;
import android.app.Activity;
import android.os.PowerManager;

public class MainActivity extends Activity {
    private static final String TAG = "anuj.android.app.accelerometergolf.MainActivity";

    private PowerManager.WakeLock mWakeLock;
    private SimulationView mSimulationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PowerManager mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);

        mSimulationView = new SimulationView(this);
        setContentView(mSimulationView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWakeLock.acquire();
        mSimulationView.startSimulation();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSimulationView.stopSimulation();
        mWakeLock.release();
    }
}
