package bean;

import util.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.*;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    private Socket server;
    private Integer portNumber;
    private File core;
    private RandomAccessFile serverLog;

    public Server(int portNumber) throws IOException {
        this.portNumber = portNumber;
        serverSocket = new ServerSocket(portNumber);
        core = new File("core.json");
        serverLog = new RandomAccessFile(new File("serverLog.xml"), "rw");
    }

    @Override
    public void run() {

        try {
            File inputFile = new File("clientInformation.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XmlParser xmlParser = new XmlParser();
            saxParser.parse(inputFile, xmlParser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (true) {

                System.out.println("\nServer : Waiting for client on port : " + serverSocket.getLocalPort());
                server = serverSocket.accept();
                System.out.println("Server : Got connection from : " + server.getInetAddress());

                BufferedReader serverReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter serverWriter = new PrintWriter(server.getOutputStream());
                serverWriter.println("Server : Welcome to my server");
                serverWriter.flush();

                String message = serverReader.readLine();
                while (!(message == null || message.equalsIgnoreCase("exit"))) {
                    System.out.println("Server : MessageReceived: " + message);
                    serverWriter.println(message);
                    serverWriter.flush();
                    message = serverReader.readLine();
                }
                server.close();
            }

        } catch (SocketTimeoutException e) {
            System.out.println("Server : Connection timed out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateServerPort() {

    }
}

