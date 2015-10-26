package main;

import bean.Client;
import bean.Server;
import org.json.simple.parser.ParseException;
import util.MyJsonParser;
import util.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Main {

    public static Integer portNumber = 3456;
    public static String ipAddress = "3.3.3.3";
    public static String xmlFilePath = "clientInformation.xml";
    public static String jsonFilePath = "core.json";

    public static void main(String[] args) {
        try {
            Thread server = null;
            server = new Server(portNumber);
            server.start();
            Thread client = null;
            client = new Client(InetAddress.getLocalHost().getHostName(), portNumber, new Socket());
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
