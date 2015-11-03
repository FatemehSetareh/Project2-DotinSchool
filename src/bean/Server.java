package bean;

import util.MyJsonParser;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server extends Thread {
    private ServerSocket serverSocket;
    private Socket server;
    private Integer portNumber;
    private Logger logger = Logger.getLogger(Server.class.getName());

    public Server() throws IOException {
    }

    @Override
    public void run() {
        try {
            /* Add logger to log server actions */
            FileHandler handler = new FileHandler("server.out", true);
            logger.addHandler(handler);

            MyJsonParser jsonParser = new MyJsonParser("core.json");
            jsonParser.parseJson();
            portNumber = jsonParser.getServerPort();
            logger.log(Level.INFO, "Json file parsed successfully.");

            while (true) {
                serverSocket = new ServerSocket(portNumber);
                logger.log(Level.INFO, "Server initialized at port : " + portNumber);
                System.out.println("Server : Waiting for client on port : " + serverSocket.getLocalPort());
                server = serverSocket.accept();
                System.out.println("Server : Got connection from  " + currentThread().getName());
                logger.log(Level.INFO, "Server got connection from client ");

                String request;
                do {
                    ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
                    request = (String) objectInputStream.readObject();
                    System.out.println("Request Received: " + request);
                    logger.log(Level.INFO, "Server received a request : " + request);

                    Transaction transaction = stringToTransaction(request);
                    logger.log(Level.INFO, "Server Convert the string request to an Transaction object.");
                    Deposit deposit = search(transaction, MyJsonParser.depositsArray);
                    logger.log(Level.INFO, "Server search his DB and find the deposit of received request.");
                    if (deposit != null) {
                        if (transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) == 1) {
                            synchronized (deposit) {
                                transaction.validateRequest(deposit);
                                logger.log(Level.INFO, "Validated the request.");
                                transaction.calculateResponse(deposit);
                                logger.log(Level.INFO, "Calculated the response.");
                                sendResponse(deposit.getDepositId(), transaction.getResult());
                                logger.log(Level.INFO, "Server sent the response to client.");
                            }
                        } else {
                            sendResponse(transaction.getTransactionDeposit(), "Failure <error <transaction amount is negative ! /> />");
                            logger.log(Level.SEVERE, "Amount of transaction is negative.");
                        }

                    } else {
                        sendResponse(transaction.getTransactionDeposit(), "Failure <error <does not find deposit /> />");
                        logger.log(Level.SEVERE, "This deposit is not in my DB.");
                    }
                } while (request != null);

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
        Integer transactionId = Integer.parseInt(str[0]);
        String transactionType = str[1];
        BigDecimal transactionAmount = new BigDecimal(str[2]);
        Integer transactionDeposit = Integer.parseInt(str[3]);
        return new Transaction(transactionId, transactionType, transactionAmount, transactionDeposit);
    }

    public Deposit search(Transaction transaction, ArrayList<Deposit> depositsArray) {
        for (Deposit deposit : depositsArray) {
            if (transaction.getTransactionDeposit().equals(deposit.getDepositId())) {
                return deposit;
            }
        }
        return null;
    }

    public void sendResponse(Integer depositId, String result) throws IOException {
        String response = "<transaction id = \"" + depositId + "\" result = \"" + result + "\" /> \n";
        ObjectOutputStream send = new ObjectOutputStream(server.getOutputStream());
        send.writeObject(response);
    }
}