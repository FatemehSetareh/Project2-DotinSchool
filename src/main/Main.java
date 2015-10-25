package main;

import bean.Client;
import bean.Server;
import org.json.simple.parser.ParseException;
import util.MyJsonParser;
import util.XmlParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class Main {

    public static Integer portNumber = 1236;

    public static void main(String[] args) throws IOException, ParseException {

        String serverName = InetAddress.getLocalHost().getHostName();

        Thread server = new Server(portNumber);
        server.start();

        Thread client = new Client(serverName, portNumber);
        client.start();

    }
}
