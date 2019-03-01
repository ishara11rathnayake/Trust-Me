package com.industrialmaster.trustme;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SubscribeActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
    }

    public void subscribe(View view){

        EditText editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        String recieverNo = editTextMobile.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("RecieverNo", recieverNo);
        editor.apply();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");

        SendSMSReceiver receiver = new SendSMSReceiver();

        registerReceiver(receiver,filter);

        Toast.makeText(this, "Subscribed", Toast.LENGTH_SHORT).show();
    }

    public void unsubscribe(View View){
        SendSMSReceiver receiver = new SendSMSReceiver();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        Toast.makeText(this, "Unsubscribed", Toast.LENGTH_SHORT).show();
    }

}
