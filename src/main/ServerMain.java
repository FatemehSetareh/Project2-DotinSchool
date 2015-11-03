package main;

import bean.Server;
import java.io.IOException;


public class ServerMain {

    public static void main(String[] args) {
        try {
            Thread server = new Server();
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
