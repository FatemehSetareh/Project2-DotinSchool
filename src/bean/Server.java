package bean;

import main.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.MyJsonParser;
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
    private RandomAccessFile serverLog;

    public Server(int portNumber) throws IOException, ParseException {
        serverSocket = new ServerSocket(portNumber);
        serverLog = new RandomAccessFile(new File("serverLog.xml"), "rw");
    }

    @Override
    public void run() {
        // MyJsonParser myJsonParser = new MyJsonParser(Main.jsonFilePath);
        // myJsonParser.parseJson();
        //  myJsonParser.updateJson();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XmlParser xmlParser = new XmlParser();
            saxParser.parse("requestToServer.xml", xmlParser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while (true) {

                System.out.println("Server : Waiting for client on port : " + serverSocket.getLocalPort());
                server = serverSocket.accept();
                System.out.println("Server : Got connection from : " + server.getInetAddress());
                //Client client = new Client(InetAddress.getLocalHost().getHostName(),Main.portNumber, server);
                //client.start();

                byte[] myByteArray = new byte[1024];
                InputStream inputStream = server.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("requestToServer.xml");
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int bytesRead = inputStream.read(myByteArray, 0, myByteArray.length);
                bufferedOutputStream.write(myByteArray, 0, bytesRead);
                bufferedOutputStream.close();

                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

