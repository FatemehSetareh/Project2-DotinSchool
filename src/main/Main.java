package main;

import bean.Client;
import bean.Server;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        String serverName = InetAddress.getLocalHost().getHostName();
        int portNumber = 9000;

        Server server = new Server(portNumber);
        server.run();

        Client client = new Client(serverName, portNumber);
        client.run();
    }
}
