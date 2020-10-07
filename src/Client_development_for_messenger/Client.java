package Client_development_for_messenger;

import java.io.*;
import java.net.Socket;

public class Client {

    private static final int PORT = 8818;
    private static final String HOST = "localhost";
    private static Socket clientSocket; //сокет для соединения
    private static BufferedReader consoleReader; // буфер для чтения из консоли

    private static InputStream in; // поток чтения из сокета
    private static OutputStream out; // поток записи в сокет

    public static void main(String[] args) {



        System.out.println("Enter the name for authorization: ");

        // Authorization
        try {
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            clientSocket = new Socket(HOST, PORT);
            out = clientSocket.getOutputStream();
            in = clientSocket.getInputStream();
            String userName = consoleReader.readLine();
            out.write(userName.getBytes());
            out.flush();

            System.out.println("Connection is ready >>>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Chatting
        String msg = "";
        try {
            while (!msg.equalsIgnoreCase("close")) {
                System.out.print(">>>> ");
                msg = consoleReader.readLine();
                out.write(msg.getBytes());
                out.flush();
            }
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }

    }

}
