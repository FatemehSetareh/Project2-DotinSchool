package bean;

import org.json.simple.parser.ParseException;
import util.MyJsonParser;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


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

            Logger logger = Logger.getLogger(Server.class.getName());
            try {
                FileHandler handler = new FileHandler("server.out", true);
                logger.addHandler(handler);
            } catch (IOException e) {
                throw new IllegalStateException("Could not add file handler.", e);
            }

            while (true) {
                System.out.println("Server : Waiting for client on port : " + serverSocket.getLocalPort());
                server = serverSocket.accept();
                System.out.println("Server : Got connection from  " + currentThread().getName());

                for (int i = 0; i <= 2; i++) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
                    String request = (String) objectInputStream.readObject();
                    System.out.println("Request Received: " + request);
                    logger.log(Level.SEVERE, "request: ", request);

                    Transaction transaction = stringToTransaction(request);
                    Deposit deposit = search(transaction, MyJsonParser.depositsArray);
                    deposit.setLock(false);

                    synchronized (deposit){
                        transaction.validateRequest(deposit);
                        transaction.calculateResponse(deposit);
                    }
                }

                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Transaction stringToTransaction(String request) {
        //logger.log(Level.SEVERE," deposit [input] : "+ request + "\n");
        String[] str = request.split(",");
        Integer transactionId = Integer.parseInt(str[0]);
        String transactionType = str[1];
        Integer transactionAmount = Integer.parseInt(str[2]);
        Integer transactionDeposit = Integer.parseInt(str[3]);
        return new Transaction(transactionId, transactionType, transactionAmount, transactionDeposit);
    }

    public Deposit search(Transaction transaction, ArrayList<Deposit> depositsArray) {
        for (Deposit deposit : depositsArray) {
            if (transaction.getTransactionDeposit().equals(deposit.getDepositId())) {
                return deposit;
            }
        }
        System.out.println("This transaction does not match with any deposit in my json file");
        return null;
    }
}