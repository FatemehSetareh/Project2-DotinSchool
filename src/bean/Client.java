package bean;

import main.Main;
import util.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Client extends Thread {
    private File terminal;
    private Integer portNumber;
    private String xmlFilePath;

    public Client(int portNumber, String xmlFilePath) throws FileNotFoundException {
        this.portNumber = portNumber;
        this.xmlFilePath = xmlFilePath;
        terminal = new File(xmlFilePath);
    }

    @Override
    public void run() {
        //update serverIp and port in client information file
//        XmlWriter xmlWriter = new XmlWriter(Main.xmlFilePath);
//        xmlWriter.updateXml();
        try {
            System.out.println("\nClient : Connecting to server ...");
            Socket client = new Socket(InetAddress.getLocalHost(), portNumber);
            System.out.println("Client : Just connected to server " + client.getLocalSocketAddress());

            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                XmlParser xmlParser = new XmlParser();
                saxParser.parse("terminal.xml", xmlParser);
            } catch (Exception e) {
                e.printStackTrace();
            }

//            //**************send whole xmlFile as a request to server
//            byte[] myByteArray = new byte[(int) terminal.length()];
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(terminal));
//            bufferedInputStream.read(myByteArray, 0, myByteArray.length);
//            OutputStream outputStream = client.getOutputStream();
//            outputStream.write(myByteArray, 0, myByteArray.length);
//            outputStream.flush();
//            System.out.println("Client : request sent");

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}