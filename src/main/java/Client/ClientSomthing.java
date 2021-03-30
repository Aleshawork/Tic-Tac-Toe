package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SocketHandler;

public class ClientSomthing {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String ipAdress;
    private int port;
    private String nickname;
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;

    public ClientSomthing( String ipAdress, int port){
        this.ipAdress=ipAdress;
        this.port=port;

        try{
            this.socket = new Socket(ipAdress,port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // todo: спросить пользователя имя

            new ReadMsg().start();
            new WriteMsg().start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ofService(){
        try{
            if(!socket.isClosed()){
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class ReadMsg  extends Thread{
        @Override
        public void run() {
            String str;
            try{
                while(true){
                    str=in.readLine();
                    if(str.equals("stop")){
                        ClientSomthing.this.ofService();
                        break;
                    }
                    // todo: продумать вывод не на консоль !
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class WriteMsg extends Thread {
        @Override
        public void run() {
            String userMsg;
           while(true){
               try{
                   time = new Date();
                   dt1= new SimpleDateFormat("HH:mm:ss");
                   dtime= dt1.format(time);

                   userMsg= inputUser.readLine();

                   if(userMsg.equals("stop")){
                       out.write("stop" +"\n");
                       ClientSomthing.this.ofService();
                       break;
                   }
                   else{
                     out.write(String.format("%s : %s --> %s \n", dtime,nickname,userMsg));
                   }
                   out.flush();

               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
