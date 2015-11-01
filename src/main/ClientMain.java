package main;

import bean.Client;
import java.io.FileNotFoundException;


public class ClientMain {
    public static void main(String[] args) {
        try {
            Thread client = new Client(1234,"terminal.xml");
            client.start();
//            Thread client1 = new Client(1234, "terminal1.xml");
//            client1.start();
//            Thread client2 = new Client(1234, "terminal2.xml");
//            client2.start();
//            Thread client3 = new Client(1234, "terminal3.xml");
//            client3.start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
