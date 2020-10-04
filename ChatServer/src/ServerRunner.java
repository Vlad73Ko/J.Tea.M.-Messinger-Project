import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerRunner extends Thread {
    private final Socket clientSocket;
    private String login = null;

    public ServerRunner(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if ("quit".equalsIgnoreCase(cmd)) {
                    break;
                } else if ("login".equalsIgnoreCase(cmd)) {
                    handleLogin(outputStream, tokens);
                    /*
                     * Тут будет сам мессенджер.
                     * Может выглядеть так:
                     * if (handleLogin(outputStream, tokens) {
                     * messenger.start
                     * }
                     *
                     * */
/*                    List users = new ArrayList();
                    for (User us : users) {
                        String msg = user.getLoginName  + ": " + line + "\n";
                        outputStream.write(msg.getBytes());
                    }*/
                } else {
                    String msg = "unknown " + cmd + "\n";
                    outputStream.write(msg.getBytes());
                }
            }

        }
        clientSocket.close();
    }

    private boolean handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String login = tokens[1];
            String password = tokens[2];

            if (login.equals("user") && password.equals("user")) {
                String msg = "ok login";
                outputStream.write(msg.getBytes());
                this.login = login;
                System.out.println("User logged in: " + login);
                return true;
            } else {
                String msg = "Login error";
                outputStream.write(msg.getBytes());
            }
        }
        return false;
    }
}