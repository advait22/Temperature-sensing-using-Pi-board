package com.example.advait.temptracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WeatherReport extends AppCompatActivity {

    private TextView alertMessage;
    private BroadcastReceiver TemperatureBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String Temperature = intent.getStringExtra("HI");
            alertMessage.setText(Temperature);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_report);
        alertMessage = (TextView) findViewById(R.id.alertMessage);
        registerReceiver(TemperatureBroadcast,new IntentFilter("com.example.advait.temptracker.ACTION_ALERT"));
    }

    public void generateTemperatureBroadcast(View view){
            sendAlert("Temperature as of now");
    }

    private void sendAlert(String Sendtemperature){
        Intent temperatureIntent = new Intent("com.example.advait.temptracker.ACTION_ALERT");
        temperatureIntent.putExtra("HI","40");
        sendBroadcast(temperatureIntent);
    }
}
