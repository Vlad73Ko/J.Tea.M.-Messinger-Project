package task_Server_development.server;


import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServerMain {


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4999);
            List<UserOnServer> usersOnServer = new ArrayList<>();  // The list of all users


            Socket socket = serverSocket.accept();                      // ждём подключения
            System.out.println("Accepted " + socket.getInetAddress());  // подключился клиент - выводим его IP

            InputStream inputStreamForLogIn = socket.getInputStream();  // сделали входной поток для получения данных пользователя

            // получение имени пользователя от клиента

            byte[] buf = new byte[32 * 1024];
            String userName = new String(buf, 0, inputStreamForLogIn.read(buf));
            System.out.println("User " + userName + " connected.");

            // if the user already exists then just change isOnLine to true, if not then add to the users list and
            // isOnLine to true
            UserOnServer user = new UserOnServer(userName, userName);

            int userIndex = new UserFinder().userIndex(user, usersOnServer); // если индекс введенного подьзователя -1, то его не существует. Вносим его в лист

            if (userIndex == -1) {
                new AddUserToList().add(user, usersOnServer);
            }
            user.logIn();


            while (true) {

//                ServerRunner serverRunner = new ServerRunner(socket, userName);
//                serverRunner.run();
//
// ------------------------------------------------------   Уходит в ServerRunner   -----------------------------------


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
//------------------------------------------------------   Уходит в ServerRunner   -----------------------------------
    }

}