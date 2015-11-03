package bean;

import util.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Client extends Thread {
    private Integer portNumber;
    private String xmlFilePath;
    private String loggerPath = "123456.log";
    private Socket client;
    RandomAccessFile responseFile = new RandomAccessFile("response.xml", "rw");
    private String response;

    public Client(int portNumber, String xmlFilePath) throws FileNotFoundException {
        this.portNumber = portNumber;
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public void run() {
        try {
//            Logger logger = Logger.getLogger(Server.class.getName());
//            try {
//                FileHandler handler = new FileHandler(loggerPath, true);
//                logger.addHandler(handler);
//            } catch (IOException e) {
//                throw new IllegalStateException("Could not add file handler.", e);
//            }

            parseXmlFile(xmlFilePath);
            connectToServer();
            serializeRequests(XmlParser.transactionsArray);

            do {
                receiveResponse();
            } while (response != null);

            // client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Socket connectToServer() {
        try {
            System.out.println("\nClient " + getName() + ": Connecting to server ...");
            client = new Socket(InetAddress.getLocalHost(), portNumber);
            System.out.println("Client " + currentThread().getName() + ": Just connected to server " + client.getLocalSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client : cant connect to server");
        }
        return client;
    }

    public void parseXmlFile(String xmlFilePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XmlParser xmlParser = new XmlParser();
            saxParser.parse(xmlFilePath, xmlParser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serializeRequests(ArrayList<Transaction> transactionsArray) throws IOException {
        for (Transaction transaction : transactionsArray) {
            String request = transaction.transactionToString();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(request);
        }
    }

    public void receiveResponse() throws IOException {
        DataInputStream receive = new DataInputStream(client.getInputStream());
        response = String.valueOf(receive.readInt());
        responseFile.writeBytes("<response initialBalance = \"" + response + "\" /> \n");
    }
}