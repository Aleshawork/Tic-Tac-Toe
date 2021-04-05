package Server;

import Dto.MapDto;
import Dto.Step;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;

public class ServerSomthing extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private GsonBuilder gsonBuilder;


    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        int number = Server.getNumber();
        System.out.println(String.format("Сервер выдал номер: %d", number));
        send(Integer.toString(number)); // send number
        gsonBuilder = new GsonBuilder();
        start();



    }


    @Override
    public void run() {
        Gson gson= gsonBuilder.create();
        String json = null;
        try {
            while (true) {

                json = in.readLine();
                System.out.println(json);

                if (json.equals("stop")) {
                    this.ofService();
                    break;
                } else {

                    Step step = new Gson().fromJson(json, Step.class);
                    if(step.getX()>Server.gameMap.getSize() || step.getY()>Server.gameMap.getSize()){
                        this.send("Координаты введены неверно !");
                    }else{
                        Server.gameMap.setPozition(step.getX(), step.getY(), step.getNumber());
                        MapDto mapDto = new MapDto(Server.gameMap.getMapOfGame());

                        String mapJson = gson.toJson(mapDto);

                        // Send game map to client
                        Server.server1.send(mapJson.replace("\n"," "));
                        Server.server2.send(mapJson.replace("\n"," "));

                        if(!Server.gameMap.endOfGame()){
                            Server.gameMap.outOnScreenGameMap();
                        }else{
                            Server.gameMap.outOnScreenGameMap();
                            this.send("You win !");
                            Server.server1.send("You lose !");
                            Server.server2.send("You lose !");
                            Server.server1.ofService();
                            Server.server2.ofService();

                        }
                    }


                    System.out.println("Echoing: " + step);
//
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(String word) {
        try {
            out.write(word + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ofService() {
        try {
            if (!socket.isClosed()) {
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




