package com.example.advait.temptracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by advait on 1/9/16.
 */
public class Client extends Activity {
    String input="";
    String content="";
    Socket so;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);
    }

    void getConnection()
    {
        try{

            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            so = new Socket("192.168.0.104",1294);
            InputStream ips = so.getInputStream();
            DataInputStream dis = new DataInputStream(ips);
            OutputStream os = so.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            System.out.println("Enter Input: ");

            while(true)
            {
                input = br.readLine();
                dos.writeUTF(input);

                content = dis.readUTF();
                if(content.equals("END")||content.equals("end"))
                {
                    so.close();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        Client obj = new Client();
        obj.getConnection();
    }
    public void onClick(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.EditText01);
            String str = et.getText().toString();
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())),true);
            out.println(str);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

