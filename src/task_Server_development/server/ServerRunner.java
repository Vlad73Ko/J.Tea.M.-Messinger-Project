package task_Server_development.server;

import java.net.Socket;

public class ServerRunner extends  Thread {

    final Socket socket;

    public ServerRunner(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {


    }
}
