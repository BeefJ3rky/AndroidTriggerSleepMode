package com.example.beast.schedulesleep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by BEAST on 4/22/2017.
 */

public class SleepModeBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.sleep")){
            System.out.println("hier je pokkie in sleepmode knallen");
        }
        else{
            System.out.println("hier is iets onverklaarbaars aan de hand");
        }

    }
}
