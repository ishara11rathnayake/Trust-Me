package com.industrialmaster.trustme;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import static android.content.Context.MODE_PRIVATE;

public class SendSMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if(extras != null){
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String phoneNo = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                String receiverNo = null;

                SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
                receiverNo = prefs.getString("RecieverNo", "0769600614");

                String smsMessage = "Your girlfriend got a call from " + phoneNo;

                String scAddress = null;
                PendingIntent sentIntent = null, deliveryIntent = null;

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(receiverNo, scAddress, smsMessage, sentIntent, deliveryIntent);
            }
        }
    }
}
