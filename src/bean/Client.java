package bean;

import main.Main;
import util.XmlParser;
import util.XmlWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Client extends Thread {
    private Socket client;
    private File clientInformation;
    private RandomAccessFile clientLog;

    public Client(String serverName, int portNumber, Socket socket) throws FileNotFoundException {
        clientInformation = new File(Main.xmlFilePath);
        clientLog = new RandomAccessFile(new File("clientLog.xml"), "rw");
    }


    @Override
    public void run() {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XmlParser xmlParser = new XmlParser();
            saxParser.parse(clientInformation, xmlParser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        XmlWriter xmlWriter = new XmlWriter(Main.xmlFilePath);
        xmlWriter.updateXml();

        try {
            System.out.println("\nClient : Connecting to server ...");
            Socket client = new Socket(InetAddress.getLocalHost(), Main.portNumber);
            System.out.println("Client : Just connected to server " + client.getLocalSocketAddress());

            PrintWriter clientWriter = new PrintWriter(client.getOutputStream());
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(clientReader.readLine());
            BufferedReader clientConsulReader = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Client : Enter message to echo or Exit to end: ");
                message = clientConsulReader.readLine();
                if (message == null || message.equalsIgnoreCase("exit"))
                    break;
                clientWriter.println(message);
                clientWriter.flush();
                System.out.println("Client : Echo from server: " + clientReader.readLine());
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}