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
        String msg = "";
        try {
            System.out.println("Receiver start");
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                msg = "Incoming Call Coming";
            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
                msg = "Call Received Coming";
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                msg = "Call Idle Coming";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Call ----------------- " + msg);
//        Intent i = new Intent(context, MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.putExtra("message", msg);
//        context.startActivity(i);

        // Anshu1506 code for not restart activity
//                Bundle extras = intent.getExtras();
//                Intent i = new Intent("broadCastName");
//                i.putExtra("message", "ANSHU");
//                context.sendBroadcast(i);

    }
}