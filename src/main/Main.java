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

    //public Integer portNumber = 1234;
    //public static String ipAddress = "4.4.4.4";
    //public static String xmlFilePath = "clientInformation.xml";
    //public static String jsonFilePath = "core.json";

    public static void main(String[] args) {
        try {
            Thread server = new Server(1234);
            server.start();
            Thread client = new Client(1234, "terminal.xml");
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
