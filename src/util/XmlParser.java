package util;

import bean.Transaction;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;

public class XmlParser extends DefaultHandler {
    public static ArrayList<Transaction> transactionsArray = new ArrayList<Transaction>();
    private String outLogPath;
    private String serverIp;
    private Integer serverPort;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("terminal")) {
            String terminalId = attributes.getValue("id");
            String terminalType = attributes.getValue("type");

        } else if (qName.equals("server")) {
            serverIp = attributes.getValue("ip");
            serverPort = Integer.parseInt(attributes.getValue("port"));

        } else if (qName.equals("outLog")) {
            outLogPath = attributes.getValue("path");

        } else if (qName.equals("transaction")) {
            Integer transactionId = Integer.parseInt(attributes.getValue("id"));
            String transactionType = attributes.getValue("type");
            BigDecimal transactionAmount = new BigDecimal(attributes.getValue("amount"));
            Integer transactionDeposit = Integer.parseInt(attributes.getValue("deposit"));

            Transaction transaction = new Transaction(transactionId, transactionType, transactionAmount, transactionDeposit);
            transactionsArray.add(transaction);
        }
    }

    public String getOutLogPath() {
        return outLogPath;
    }

    public String getServerIp() {
        return serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }
}
