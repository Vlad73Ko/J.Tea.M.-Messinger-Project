package Client_development_for_messenger;

import java.io.*;
import java.net.Socket;

public class ClientMain {

    private static final int PORT = 4999;
    private static final String HOST = "localhost";
    private static Socket clientSocket; //сокет для соединения
    private static BufferedReader consoleReader; // буфер для чтения из консоли
    private static BufferedReader inBuffer; // буфер для чтения из сокета
    private static BufferedWriter outBuffer; // буфер для записи в сокет

    public static void main(String[] args) {

        try {
            try {
                clientSocket = new Socket(HOST, PORT);
                consoleReader = new BufferedReader(new InputStreamReader(System.in));
                inBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outBuffer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String msg = consoleReader.readLine(); // чтение из консоли
                outBuffer.write(String.valueOf(msg.getBytes())+ "\n");// + "\n");   // отправка через out
                outBuffer.flush();
            } finally {
                clientSocket.close(); // закрытие сокета
                inBuffer.close();   // и всех буферов
                outBuffer.close();
                consoleReader.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
