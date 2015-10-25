package util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class XmlParser extends DefaultHandler {
    private String terminalId;
    private String terminalType;
    private String serverIp;
    private String serverPort;
    private String outLogPath;
    private Integer transactionId;
    private String transactionType;
    private Integer transactionAmount;
    private Integer transactionDeposit;

    public XmlParser() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("terminal")) {
            terminalId = attributes.getValue("id");
            terminalType = attributes.getValue("type");
            System.out.println(" \n\n terminalId :" + terminalId
                    + " \n terminalType :" + terminalType);

        } else if (qName.equals("server")) {
            serverIp = attributes.getValue("ip");
            serverPort = attributes.getValue("port");
            System.out.println(" serverIp :" + serverIp
                    + " \n serverPort :" + serverPort);

        } else if (qName.equals("outLog")) {
            outLogPath = attributes.getValue("path");
            System.out.println(" outLogPath :" + outLogPath);

        } else if (qName.equals("transaction")) {
            transactionId = Integer.parseInt(attributes.getValue("id"));
            transactionType = attributes.getValue("type");
            transactionAmount = Integer.parseInt(attributes.getValue("amount"));
            transactionDeposit = Integer.parseInt(attributes.getValue("deposit"));
            System.out.println(" \ntransactionId :" + transactionId
                    + " \n transactionType :" + transactionType
                    + " \n transactionAmount :" + transactionAmount
                    + " \n transactionDeposit :" + transactionDeposit);
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("terminal")) {
//            System.out.println(" \n End Element :" + qName);
        }
    }
}
