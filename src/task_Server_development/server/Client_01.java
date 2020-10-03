package task_Server_development.server;

import jdk.jshell.execution.Util;

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

public class Client_01 {


    public static void main(String[] args) throws IOException {

        Scanner userNameScanner = new Scanner(System.in);
        System.out.println("Hi, please enter your name.");
        String userName = userNameScanner.nextLine();

        Socket socket = null;
        Scanner messageScanner = new Scanner(System.in);


        try {
            socket = new Socket("localhost", 4999);


            OutputStream outputStreamForLogIn = socket.getOutputStream();
            outputStreamForLogIn.write(userName.getBytes());
            outputStreamForLogIn.flush();
            System.out.println("Let's start!");


            while (true) {
                try {
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();

                    String line = messageScanner.nextLine();
                    outputStream.write(line.getBytes());
                    outputStream.flush();

                    byte[] data = new byte[32 * 1024];

                    String response = new String(data, 0, inputStream.read(data));
                    if (response.equals("close")) {
                        System.out.println("The Server disconnected.");
                        socket.close();
                        return;
                    }

                    System.out.println("Server > " + response);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("get wrong");
                }
            }
        } finally {
            Objects.requireNonNull(socket).close();
        }


    }
}


