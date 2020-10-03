package task_Server_development.server;

import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.Objects;

public class ServerMain {


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4999);

            Socket socket = serverSocket.accept();
            System.out.println("Accepted " + socket.getInetAddress());

            InputStream inputStreamForLogIn = socket.getInputStream();

            byte[] buf = new byte[32 * 1024];

            String userName = new String(buf, 0, inputStreamForLogIn.read(buf));
            System.out.println("User " + userName + " connected.");


            while (true) {

                try {
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();

                    String line = new String(buf, 0, inputStream.read(buf));

                    System.out.println(userName + " > " + line);

                    outputStream.write(line.getBytes());
                    outputStream.flush();

                    if (line.equals("close")) {
                        System.out.println("User " + userName + " disconnected.");
                        serverSocket.close();
                        return;
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            Objects.requireNonNull(serverSocket).close();

        }

    }

}