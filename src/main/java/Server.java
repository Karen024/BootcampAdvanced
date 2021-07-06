import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private final int port;
    private List<ServerService> listOfServices;

    public Server(int port) {
        this.port = port;
        listOfServices = new ArrayList<>();
    }

    public List<ServerService> getListOfServices() {
        return listOfServices;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                ServerService serverService = new ServerService(this,client);
                listOfServices.add(serverService);
                serverService.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeLoggedOffFromList(ServerService serverService) {
        listOfServices.remove(serverService);
    }
}
