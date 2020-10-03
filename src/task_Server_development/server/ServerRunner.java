package task_Server_development.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ServerRunner extends  Thread {

    final Socket socket;
    String userName;

    public ServerRunner(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
    }

    @Override
    public void run() {
        while (true) {

            try {

                byte[] buf = new byte[32 * 1024];

                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                String line = new String(buf, 0, inputStream.read(buf));

                System.out.println(userName + " > " + line);

                outputStream.write(line.getBytes());
                outputStream.flush();

                if (line.equals("close")) {
                    System.out.println("User " + userName + " disconnected.");
                    socket.close();
                    return;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

