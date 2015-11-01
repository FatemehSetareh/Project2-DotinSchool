package bean;

import util.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;


public class Client extends Thread {
    private Integer portNumber;
    private String xmlFilePath;
    private Socket client;
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    //ObjectOutputStream objectOutputStream;

    public Client(int portNumber, String xmlFilePath) throws FileNotFoundException {
        this.portNumber = portNumber;
        this.xmlFilePath = xmlFilePath;
    }

    @Override
    public void run() {
        try {
            parseXmlFile(xmlFilePath);
            clientConnect();

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket clientConnect() {
        try {
            System.out.println("\nClient " + getName() + ": Connecting to server ...");
            client = new Socket(InetAddress.getLocalHost(), portNumber);
            System.out.println("Client " + getName() + ": Just connected to server " + client.getLocalSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client : cant connect to server");
        }
        return client;
    }
    public void parseXmlFile(String xmlFilePath){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XmlParser xmlParser = new XmlParser();
            saxParser.parse(xmlFilePath, xmlParser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void sendRequestObjects(ArrayList<Transaction> arrayList) throws IOException {
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
//        for (Transaction transaction : arrayList) {
//            objectOutputStream.writeObject(transaction);
//            objectOutputStream.flush();
//            objectOutputStream.close();
//            System.out.println("sending done!");
//        }
//    }

}