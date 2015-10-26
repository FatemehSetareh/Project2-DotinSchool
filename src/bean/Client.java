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
//        XmlWriter xmlWriter = new XmlWriter(Main.xmlFilePath);
//        xmlWriter.updateXml();
        try {
            System.out.println("\nClient : Connecting to server ...");
            Socket client = new Socket(InetAddress.getLocalHost(), Main.portNumber);
            System.out.println("Client : Just connected to server " + client.getLocalSocketAddress());

            byte[] myByteArray = new byte[(int) clientInformation.length()];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(clientInformation));
            bufferedInputStream.read(myByteArray, 0, myByteArray.length);
            OutputStream outputStream = client.getOutputStream();
            outputStream.write(myByteArray, 0, myByteArray.length);
            outputStream.flush();

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}