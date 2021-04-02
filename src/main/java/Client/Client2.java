package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
    public static  String ipAddres="localhost";
    public static int port =8081;

    public static void main(String[] args) {
        new ClientSomthing(ipAddres,port);
    }
}
