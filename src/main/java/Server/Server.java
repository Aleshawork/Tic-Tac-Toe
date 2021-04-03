package Server;

import Dto.Step;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;


public class Server {
    public static final int PORT=8081;
    //public static LinkedList<ServerSomthing> serverSomthingLinkedList = new LinkedList<ServerSomthing>();
    public static ServerSomthing server1=null;
    public static ServerSomthing server2=null;
    public static int number=0;

    public static int getNumber(){
        number++;
        return number;
    }



    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Сервер запустился ...");

        try {
            while(true){
                Socket socket =server.accept();
                try{
                    if(Server.server1==null){
                        server1=new ServerSomthing(socket);
                        System.out.println("Первый пользоваель подключился !");

                    }else if(Server.server2==null){
                        server2= new ServerSomthing(socket);
                        System.out.println("Второй пользователь подключился !");

                    }

                }catch (IOException ex){
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




 class ServerSomthing extends Thread{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerSomthing(Socket socket) throws IOException {
        this.socket=socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        int number = Server.getNumber();
        System.out.println(String.format("Сервер выдал номер: %d",number));
        send(Integer.toString(number)); // send number

        // todo: подумать о передаче предыдущих сообщений при заходе в чат
        start();
    }


    @Override
    public void run() {
      String json;
     // todo: продумать считывание имени

        try {
            while (true) {
               // send(Integer.toString(Server.getNumber()));  // send number
                json=in.readLine();
                System.out.println(json);


                if (json.equals("stop")) {
                    this.ofService();
                    break;
                } else {

                    Step step = new Gson().fromJson(json,Step.class);
                    System.out.println("Echoing: " + step);
                    // todo: добавление слова в историю
//                    for (ServerSomthing el : Server.serverSomthingLinkedList) {
//                        el.send(word);
//                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void send(String word) {
        try{
            out.write(word + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ofService() {
        try{
            if(!socket.isClosed()){
                socket.close();
                in.close();
                out.close();
                // todo: удалить из списка

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



