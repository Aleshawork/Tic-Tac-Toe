package Server;

import Dto.Step;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    public static final int PORT = 8081;
    public static ServerSomthing server1 = null;
    public static ServerSomthing server2 = null;
    public static int number = 0;
    public static boolean end = false;
    public static GameMap gameMap;

    public static int getNumber() {
        number++;
        return number;
    }


    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Сервер запустился ...");
        gameMap= new GameMap();

        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    if (Server.server1 == null) {
                        server1 = new ServerSomthing(socket);
                        System.out.println("Первый пользоваель подключился !");


                    } else if (Server.server2 == null) {
                        server2 = new ServerSomthing(socket);
                        System.out.println("Второй пользователь подключился !");
                        server1.send("start");
                        server2.send("start");


                    }

                } catch (IOException ex) {
                    System.out.println("Сокет закрылся !");
                    socket.close();
                }
            }

        } finally {
            System.out.println("Сервер завершил работу !");
            server.close();
        }

    }
}


