package server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;


public class ServerConnection {
    public static Socket socket = null;
    public static BufferedReader br = null;
    public static BufferedWriter bw = null;
    public static InetAddress host = null;

    public static void Initialize(){
        try{
            host = InetAddress.getByName("127.0.0.1");
            socket = new Socket(host,6666);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            if(new JSONObject(br.readLine()).getInt("response_code") == 200){
                System.out.println("Polaczono z Serwerem!!");
            }else{
                System.out.println("Error");
            }
        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
