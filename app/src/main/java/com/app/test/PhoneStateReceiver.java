package com.app.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            System.out.println("Receiver start");
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                System.out.println("Incoming Call State");

            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
                System.out.println("Call Received State");
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                System.out.println("Call Idle State");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Bundle extras = intent.getExtras();
        Intent i = new Intent("broadCastName");
        // Data you need to pass to activity
        i.putExtra("message", "ANSHU");

        context.sendBroadcast(i);

    }
}