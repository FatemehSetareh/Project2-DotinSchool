package bean;

import util.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends Thread {
    private Integer portNumber;
    private String serverIP;
    private String outLogPath;
    private String xmlFilePath;
    private Socket client;
    private String response;
    RandomAccessFile responseFile = new RandomAccessFile("response.xml", "rw");
    private Logger logger = Logger.getLogger(Client.class.getName());

    public Client(String xmlFilePath) throws FileNotFoundException {
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public void run() {
        try {
            /*Parse XML file*/
            parseXmlFile(xmlFilePath);
            logger.log(Level.INFO, "Xml file parsed successfully. ");
            /* Add logger to log client actions */
            FileHandler handler = new FileHandler(outLogPath, true);
            logger.addHandler(handler);

            connectToServer();
            logger.log(Level.INFO, "Client connected to server on port : " + portNumber + ".");

            serializeRequests(XmlParser.transactionsArray);
            logger.log(Level.INFO, "Client serialized his request to send to server.");

            do {
                receiveResponse();
                logger.log(Level.INFO, "Client received his response.");
            } while (response != null);

            // client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Socket connectToServer() {
        try {
            System.out.println("\nClient " + getName() + ": Connecting to server ...");
            client = new Socket(serverIP, portNumber);
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
            outLogPath = xmlParser.getOutLogPath();
            portNumber = xmlParser.getServerPort();
            serverIP = xmlParser.getServerIp();
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

    public void receiveResponse() throws IOException, ClassNotFoundException {
        ObjectInputStream receive = new ObjectInputStream(client.getInputStream());
        response = (String) receive.readObject();
        System.out.println(response);
        /*Write response in response.xml file*/
        responseFile.writeBytes(response);
        logger.log(Level.INFO, "response : " + response);
    }
}