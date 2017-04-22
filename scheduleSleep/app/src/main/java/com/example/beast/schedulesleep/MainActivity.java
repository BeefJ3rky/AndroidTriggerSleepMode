package com.example.beast.schedulesleep;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TimePicker tp;
    Button btSetTimer;
    Switch swEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
                cal.set(Calendar.MINUTE, tp.getCurrentMinute());
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                PendingIntent pi = PendingIntent.getService(
                        MainActivity.this,
                        0,
                        new Intent("com.example.sleep"),
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
                am.set(AlarmManager.RTC_WAKEUP, 0, pi);
                btSetTimer.setEnabled(false);
                swEnable.setEnabled(true);
                swEnable.setChecked(true);
            }
        });
        swEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                    PendingIntent pi = PendingIntent.getService(
                            MainActivity.this,
                            0,
                            new Intent("com.example.sleep"),
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
                    am.cancel(pi);
                    swEnable.setEnabled(false);
                    btSetTimer.setEnabled(true);
                    swEnable.setChecked(false);
                }
            }
        });
    }

    private void init(){
        tp = (TimePicker) findViewById(R.id.tpSetSleepTime);
        btSetTimer = (Button) findViewById(R.id.btnSetSleepTimer);
        swEnable = (Switch) findViewById(R.id.swEnabled);
        swEnable.setEnabled(false);
    }
}
