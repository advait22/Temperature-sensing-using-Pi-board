package com.example.advait.temptracker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by advait on 1/9/16.
 */
public class Client {
    String input="";
    String content="";
    void getConnection()
    {
        try{

            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            Socket so = new Socket("192.168.0.104",1294);
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


}
