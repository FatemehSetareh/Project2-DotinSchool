package main;

import bean.Client;
import bean.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        String serverName = InetAddress.getLocalHost().getHostName();
        int portNumber = 1236;

        Thread server = new Server(portNumber);
        server.start();

        Thread client = new Client(serverName, portNumber);
        client.start();

        JSONParser jsonParser =  new JSONParser();


    }
}
