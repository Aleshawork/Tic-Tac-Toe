package Client;

public class Client {
    public static  String ipAddres="localhost";
    public static int port =8080;

    public static void main(String[] args) {
        new ClientSomthing(ipAddres,port);
    }
}
