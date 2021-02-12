package com.app.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PhoneStateReceiver extends BroadcastReceiver {
    String baseUrl = "http://10.0.2.2:5656/users";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = "---------------------";
        try {
            System.out.println("Receiver start");
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                msg = "Incoming Call Coming";
            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
                msg = "Call Received Coming";
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                msg = "Call Idle Coming";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Call ----------------- " + msg);

        if (msg.equals("Call Idle Coming")) {
            HashMap<String, String> headersa = new HashMap<String, String>();
            headersa.put("Call", msg);

            JsonObjectRequest req = new JsonObjectRequest(baseUrl, new JSONObject(headersa),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // handle response
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // handle error
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("CUSTOM_HEADER", "Yahoo");
                    headers.put("ANOTHER_CUSTOM_HEADER", "Google");
                    return headers;
                }
            };

            MySingleton.getInstance(context).addToRequestQueue(req);
        }

    }
}