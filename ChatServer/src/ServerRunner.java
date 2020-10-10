import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerRunner extends Thread {
    private final Socket clientSocket;
    private String login = null;

    User user1 = new User("user", "user");
    User user2 = new User("guest", "guest");

    List<User> userList = new ArrayList();


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
                } else if ("send".equalsIgnoreCase(cmd)) {
                    tokens[1] = "";
                    messenger(tokens, line, outputStream);
                }

                }
            }
        clientSocket.close();
    }

    private void messenger(String[] tokens, String line, OutputStream outputStream) throws IOException {
        String msg = login + ": " + tokens.toString() + "\n";
        outputStream.write(msg.getBytes());
    }

    private boolean handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String login = tokens[1];
            String password = tokens[2];

            if (login.equals(user1.getName()) && password.equals(user1.getPassword())) {
                String msg = "ok login\n";
                outputStream.write(msg.getBytes());
                this.login = login;
                System.out.println("User logged in: " + login);
                userList.add(user1);
                user1.setOnline(true);
                return true;
            } else if (login.equals(user2.getName()) && password.equals(user2.getPassword())) {
                String msg = "ok login\n";
                outputStream.write(msg.getBytes());
                this.login = login;
                System.out.println("User logged in: " + login + "\n");
                userList.add(user2);
                user2.setOnline(true);
                return true;
            } else {
                String msg = "Login error\n";
                outputStream.write(msg.getBytes());
            }
        }
        return false;
    }
}