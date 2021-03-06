package com.example.advait.temptracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by advait on 1/9/16.
 */
public class Server extends Activity{
    String inputLine = "";
    //String line = "";
    private TextView text;
    ServerSocket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);
        text = (TextView) findViewById(R.id.text2);
    }

    void getConnection()
    {
        try{
            s = new ServerSocket(1294);
            Socket so = s.accept();
            InputStream ips;
            DataInputStream dis;
            DataOutputStream dos;
            OutputStream ops;
            //File fobj;
           // FileReader fr;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ips = so.getInputStream();
            dis = new DataInputStream(ips);
            ops = so.getOutputStream();
            dos = new DataOutputStream(ops);
            //String line = "";
            while(true)
            {
                inputLine = dis.readUTF();
                System.out.println(""+inputLine);
                dos.writeUTF(inputLine);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void onStop() {
        super.onStop();
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        Server obj2 = new Server();
        obj2.getConnection();
    }

}
