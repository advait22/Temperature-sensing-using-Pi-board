package com.example.advait.temptracker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketDemoActivity extends AppCompatActivity {

    private static final String TAG = "SocketDemoActivity";
    private static final String TAG1 = "HI";
    private static final long START_AFTER_TIME = 1 * 1000;
    private static final long FREQUENCY = 3 * 1000;

    private TextView temperature;
    private String HOST = "192.168.0.120";
    private final int PORT = 1297;

    private Timer timer;

    private TimerTask socketTimerTask = new TimerTask() {
        @Override
        public void run() {
            connectToSocket(HOST, PORT);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_demo);
        temperature = (TextView) findViewById(R.id.temperature);
        Bundle bundle = getIntent().getExtras();
        //Intent intent = getIntent();
        if (bundle != null) {
            Log.d(TAG, "HOST " + HOST + "     PORT " + PORT);
            HOST = bundle.getString("IPAddress", "");

            //connectToSocket(HOST, PORT);
            timer = new Timer();
            timer.schedule(socketTimerTask, START_AFTER_TIME, FREQUENCY);

        } else {

            Toast.makeText(SocketDemoActivity.this, "BAD", Toast.LENGTH_SHORT).show();
        }
    }

    private void connectToSocket(String host, int port) {
        Log.d(TAG1, "connectToSocket:--------- ");
        SocketTask socketTask = new SocketTask(host, port);
        Log.d(TAG1, "HOST " + HOST + "     PORT " + PORT);

        socketTask.execute();
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        timer = null;
        super.onDestroy();
    }

    class SocketTask extends AsyncTask<Void, Void, String> {

        private String host;
        private int port;

        public SocketTask(final String address, final int port) {
            this.host = address;
            this.port = port;
        }

        @Override
        protected String doInBackground(Void... params) {
            String message = "";
            try {
                Log.d(TAG, "HOST " + HOST + "     PORT " + PORT);
                Socket clientSocket = new Socket(host, port);

                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    message = bufferedReader.readLine();
                    Log.d(TAG, "Message=" + message);
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: ", e);
                }
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: ", e);
            }
            return message;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            // display message on screen
            Log.d(TAG1, "onPostExecute: ----------");
            temperature.setText(aVoid);

        }
    }
}
