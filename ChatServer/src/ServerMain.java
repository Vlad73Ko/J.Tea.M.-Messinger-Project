import java.io.*;
import java.net.*;

public class ServerMain {

    public static void main(String[] args) {
        int port = 8818;
        Peers peer = new Peers(port);
        peer.start();
    }
}


