import java.net.*;
import java.io.*;

/**
 * Created by advait on 1/9/16.
 */
public class Server {
    String inputLine = "";
    //String line = "";

    void getConnection()
    {
        try{
            ServerSocket s = new ServerSocket(1294);
            Socket so = s.accept();
            InputStream ips;
            DataInputStream dis;
            DataOutputStream dos;
            OutputStream ops;
            File fobj;
            FileReader fr;
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

    public static void main(String args[])
    {
        Server obj2 = new Server();
        obj2.getConnection();
    }

}
