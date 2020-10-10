import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Peers extends Thread{
    private final int serverPort;
    private List<ServerRunner> serverRunners= new ArrayList<>();

    public Peers(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                ServerRunner runner = new ServerRunner(clientSocket);
                serverRunners.add(runner);
                runner.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
