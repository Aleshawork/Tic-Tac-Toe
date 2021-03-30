package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class Server {
    public static final int PORT=8080;
    public static LinkedList<ServerSomthing> serverSomthingLinkedList = new LinkedList<ServerSomthing>();


    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Сервер запустился ...");

        try {
            while(true){
                Socket socket =server.accept();
                try{
                    serverSomthingLinkedList.add(new ServerSomthing(socket));
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
        // todo: подумать о передаче предыдущих сообщений при заходе в чат
        start();
    }


    @Override
    public void run() {
      String word;
     // todo: продумать считывание имени

        try {
            while (true) {
                word=in.readLine();


                if (word.equals("stop")) {
                    this.ofService();
                    break;
                } else {
                    System.out.println("Echoing: " + word);
                    // todo: добавление слова в историю
                    for (ServerSomthing el : Server.serverSomthingLinkedList) {
                        el.send(word);
                    }
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



