package bean;

import org.json.simple.parser.ParseException;
import util.MyJsonParser;
import util.Processor;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server extends Thread {
    private ServerSocket serverSocket;
    private Socket server;
    private Integer portNumber;

    public Server(int portNumber) throws IOException, ParseException {
        this.portNumber = portNumber;
        serverSocket = new ServerSocket(portNumber);
    }

    @Override
    public void run() {
        try {
            MyJsonParser jsonParser = new MyJsonParser("core.json");
            jsonParser.parseJson();

            while (true) {
                System.out.println("Server : Waiting for client on port : " + serverSocket.getLocalPort());
                server = serverSocket.accept();
                System.out.println("Server : Got connection from  " + server.getInetAddress());

                ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
                String request = (String) objectInputStream.readObject();
                System.out.println("Request Received: " + request);


                System.out.println(stringToTransaction(request));

                Processor processor = new Processor(stringToTransaction(request));
                processor.process();


                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Transaction stringToTransaction(String request) {
        String[] str = request.split(",");
        Integer transactionId = Integer.getInteger(str[0]);
        String transactionType = str[1];
        Integer transactionAmount = Integer.getInteger(str[2]);
        Integer transactionDeposit = Integer.getInteger(str[3]);
        return new Transaction(transactionId, transactionType, transactionAmount, transactionDeposit);
    }
}