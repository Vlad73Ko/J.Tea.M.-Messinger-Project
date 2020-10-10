package Client_development_for_messenger;

import java.io.*;
import java.net.Socket;

public class Client {

    private static final int PORT = 8818;
    private static final String HOST = "localhost";
    private static Socket clientSocket; //сокет для соединения
    private static BufferedReader consoleReader; // буфер для чтения из консоли

    //private static InputStream in; // поток чтения из сокета
    private static BufferedReader in; // поток чтения из сокета
    //private static OutputStream out; // поток записи в сокет
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {



        System.out.println("Enter the name for authorization: ");

        // Authorization
        try {
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            clientSocket = new Socket(HOST, PORT);
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String userName = consoleReader.readLine();
            out.write(userName + "\n");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String serverAnswer;
        try {
            System.out.println("waiting for server' answer...");
            serverAnswer = in.readLine();
            if (serverAnswer.equalsIgnoreCase("ok login")) System.out.println("Connection is ready >>>");
            else
                if (serverAnswer.equalsIgnoreCase("Login error")) System.out.println("Connection is not ready >>>");

        } catch (IOException e) {
            e.printStackTrace();
        }




        //Chatting
        String msg = "";
        try {
            // while (!clientSocket.isClosed())
            while (!msg.equalsIgnoreCase("quit")) {
                System.out.print(">>>> ");
                msg = consoleReader.readLine();
                out.write(msg + "\n");
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
