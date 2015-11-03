package main;

import bean.Client;

import java.io.FileNotFoundException;


public class ClientMain {
    public static void main(String[] args) {
        try {
            Thread client = new Client("terminal.xml");
            client.start();
            Thread client1 = new Client("terminal1.xml");
            client1.start();
            Thread client2 = new Client("terminal2.xml");
            client2.start();
//            Thread client3 = new Client("terminal3.xml");
//            client3.start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
