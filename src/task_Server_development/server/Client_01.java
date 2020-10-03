package task_Server_development.server;

import jdk.jshell.execution.Util;

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

public class Client_01 {

    public static void main(String[] args) throws IOException {
        while (true)
        {
            Socket socket = null;
            Scanner scanner = new Scanner(System.in);
            try {
                socket = new Socket("localhost", 4999);
                try {
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();

                    String line = scanner.nextLine();
                    outputStream.write(line.getBytes());
                    outputStream.flush();

                    byte[] data = new byte[32 * 1024];
                    int readyBytes = inputStream.read(data);

                    System.out.println("Server > " + new String(data, 0, readyBytes));

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("get wrong");
                }
            }
            finally {
                //Objects.requireNonNull(socket).close();
                Objects.requireNonNull(socket).close();

            }

        }
    }

}
