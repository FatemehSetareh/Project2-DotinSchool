package bean;

import org.json.simple.parser.ParseException;
import util.MyJsonParser;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server extends Thread {
    private ServerSocket serverSocket;
    private Socket server;
    private Integer portNumber;
    public static ArrayList<Client> clientsArray = new ArrayList<Client>();

    public Server(int portNumber) throws IOException, ParseException {
        serverSocket = new ServerSocket(portNumber);
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        try {
            MyJsonParser jsonParser = new MyJsonParser("core.json");
            jsonParser.parseJson();

            while (true) {
                System.out.println("Server : Waiting for client on port : " + serverSocket.getLocalPort());
                server = serverSocket.accept();
                System.out.println("Server : Got connection from : " + server.getInetAddress());

//               while(bufferedReader.ready()){
//                    receiveRequestObject();
//                    System.out.println("received!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//             }

                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public void acceptClient(){
//        Thread thread = new Thread( new Runnable() {
//            @Override
//            public void run() {
//                clientsArray.ensureCapacity(10);
//                clientsArray.add(new Client(1234,))
//            }
//        });
//        thread.start();
//
//    }



//    public Transaction receiveRequestObject() throws IOException, ClassNotFoundException {
//        ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
//        while (objectInputStream.available()){
//
//        }
//        Transaction transaction = (Transaction)objectInputStream.readObject();
//        requestQueue.add(transaction);
//        System.out.println("Server : " + transaction.toString());
//        objectInputStream.close();
//
//        return transaction;
//    }
}