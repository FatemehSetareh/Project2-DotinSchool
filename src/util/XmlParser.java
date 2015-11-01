package util;

import bean.Client;
import bean.Transaction;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;

public class XmlParser extends DefaultHandler {
    public static ArrayList<Transaction> transactionsArray = new ArrayList<Transaction>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("terminal")) {
            String terminalId = attributes.getValue("id");
            String terminalType = attributes.getValue("type");
            System.out.println(" \n\n terminalId :" + terminalId
                    + " \n terminalType :" + terminalType);

        } else if (qName.equals("server")) {
            String serverIp = attributes.getValue("ip");
            String serverPort = attributes.getValue("port");
            System.out.println(" serverIp :" + serverIp
                    + " \n serverPort :" + serverPort);

        } else if (qName.equals("outLog")) {
            String outLogPath = attributes.getValue("path");
            System.out.println(" outLogPath :" + outLogPath);

        } else if (qName.equals("transaction")) {
            Integer transactionId = Integer.parseInt(attributes.getValue("id"));
            String transactionType = attributes.getValue("type");
            Integer transactionAmount = Integer.parseInt(attributes.getValue("amount"));
            Integer transactionDeposit = Integer.parseInt(attributes.getValue("deposit"));
            System.out.println(" \ntransactionId :" + transactionId
                    + " \nType :" + transactionType
                    + " \nAmount :" + transactionAmount
                    + " \nDeposit :" + transactionDeposit);

            System.out.println("... One transaction is ready to send ...");
            Transaction transaction = new Transaction();
            transactionsArray.add(transaction);
            System.out.println(transactionsArray);
        }
    }
}
