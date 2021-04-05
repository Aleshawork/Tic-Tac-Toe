package Client;

import Dto.MapDto;
import Dto.Step;
import Server.GameMap;
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
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;
    private GsonBuilder gsonBuilder;
    private   int numberClient;
    private WriteMsg writeMsg;
    private ReadMsg readMsg;
    private MapDto mapDto;

    public String getIpAdress() {
        return ipAdress;
    }

    public int getPort() {
        return port;
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



            writeMsg = new WriteMsg();
            writeMsg.start();
            readMsg = new ReadMsg();
            readMsg.start();

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
                readMsg.stop();
                writeMsg.stop();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class ReadMsg  extends Thread{
        @Override
        public void run() {
            String message;

            try{
                while(true){
                    message=in.readLine();
                    if(message.equals("You lose !" ) || message.equals("You win !"))
                    {
                        out.write("stop\n");
                        out.flush();
                        System.out.println(message);
                        ClientSomthing.this.ofService();
                        break;

                    }else{
                        // todo: сделать проверку вводимых координат на клиенте (вводимая координата свободна)
                        mapDto = new Gson().fromJson(message,MapDto.class);
                        // Show map of game
                        mapDto.outOnScreenGameMap();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class WriteMsg extends Thread {
        @Override
        public void run() {

            try{
                numberClient= Integer.parseInt(in.readLine());
                System.out.println("Server set you name:"+numberClient);

                // waiting for connection all players
                while(true){
                    if(in.readLine().equals("start")) break;
                }
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
                   System.out.println(numberClient+" "+ dtime +" "+ x+ " "+y);
                   Step step = new Step(numberClient,dtime,x,y);
                   String jsonString =gson.toJson(step);

                   out.write(jsonString.replace("\n"," ")+"\n");
                   out.flush();


               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
