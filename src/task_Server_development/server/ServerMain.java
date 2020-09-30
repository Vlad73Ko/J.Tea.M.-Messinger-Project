package task_Server_development.server;

import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.Objects;

public class ServerMain {


    public static void main(String[] args) throws IOException {
        while (true) {

            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(4999);
                //System.out.println("Started, waiting for connection");
                Socket socket = serverSocket.accept();
                System.out.println("Accepted " + socket.getInetAddress());


                try {
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();

                    byte[] buf = new byte[32 * 1024];
                    int readyBytes = inputStream.read(buf);
                    String line = new String(buf, 0, readyBytes);

                    System.out.println("Client > " + line);

                    outputStream.write(line.getBytes());
                    outputStream.flush();

                    String response = "Received";
                    outputStream.write(response.getBytes());
                    outputStream.flush();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                Objects.requireNonNull(serverSocket).close();
            }

        }
    }
}