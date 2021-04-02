package Client;

import Dto.Step;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sun.nio.cs.StandardCharsets;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SocketHandler;

public class ClientSomthing {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader inputUser;
    private String ipAdress;
    private int port;
    private String nickname;
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;
    private GsonBuilder gsonBuilder;
    private   int numberClient;

    public String getIpAdress() {
        return ipAdress;
    }

    public int getPort() {
        return port;
    }

    public String getNickname() {
        return nickname;
    }

    public String getDtime() {
        return dtime;
    }

    public ClientSomthing(String ipAdress, int port){
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
            gsonBuilder= new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out =new PrintWriter(socket.getOutputStream(), true);


           // new ReadMsg().start();
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


//    private class ReadMsg  extends Thread{
//        @Override
//        public void run() {
//            String json;
//
//            try{
//                while(true){
//                    json=in.readLine();
//                    if(json.equals("stop")){
//                        ClientSomthing.this.ofService();
//                        break;
//                    }
//                    // todo: продумать вывод не на консоль !
//                    Step step = new Gson().fromJson(json, Step.class);
//                    System.out.println(step);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public class WriteMsg extends Thread {
        @Override
        public void run() {

            try{
                System.out.println("Server set you name:"+numberClient);
                numberClient= Integer.parseInt(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Gson gson= gsonBuilder.create();
            int x;
            int y;
           while(true){
               try{

                   time = new Date();
                   dt1= new SimpleDateFormat("HH:mm:ss");
                   dtime= dt1.format(time);

                   System.out.print("Введите x:");
                  x= Integer.parseInt(inputUser.readLine());
                   System.out.print("Введите y:");
                   y= Integer.parseInt(inputUser.readLine());
                   System.out.println(nickname+" "+ dtime +" "+ x+ " "+y);
                   Step step = new Step(nickname,numberClient,dtime,x,y);
                   String jsonString =gson.toJson(step)+"\n";

                   out.write(jsonString.replace("\n"," ")+"\n");
                   out.flush();

//                   if(userMsg.equals("stop")){
//                       out.write("stop" +"\n");
//                       ClientSomthing.this.ofService();
//                       break;
//                   }
//                   else{
//                     out.write(String.format("%s : %s --> %s \n", dtime,nickname,userMsg));
//                   }
//                   out.flush();

               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
