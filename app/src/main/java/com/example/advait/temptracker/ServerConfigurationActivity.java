package com.example.advait.temptracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ServerConfigurationActivity extends AppCompatActivity {
    private static final String TAG = "LOG";
   // private String IPAddress1;
    private String IPAddress;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server__configuration);
        editText = (EditText) findViewById(R.id.EnterIPAddress);
        //

        //IPAddress1 = IPAddress.getText().toString();

    }

    public void TemperatureDisplay(View view) {

        //Bundle bundle = new Bundle();
        //bundle.putString("CurrentTemperature",IPAddress1);
        IPAddress = editText.getText().toString();
        Intent intent = new Intent(getApplicationContext(), SocketDemoActivity.class);
        intent.putExtra("IPAddress", IPAddress);
        Log.d(TAG, "onCreate: " + IPAddress);
        startActivity(intent);

    }
}
