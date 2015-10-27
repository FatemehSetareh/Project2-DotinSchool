package util;

import bean.Transaction;
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
    ArrayList<Transaction> transactionsArray = new ArrayList<Transaction>();

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
                    + " \nType :" + transactionType
                    + " \nAmount :" + transactionAmount
                    + " \nDeposit :" + transactionDeposit);
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("terminal")) {
            Transaction transaction = new Transaction();
            transactionsArray.add(transaction);
            Process process = new Process(transaction);
            process.run();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("request checked, response calculated, ready to send");
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getOutLogPath() {
        return outLogPath;
    }

    public void setOutLogPath(String outLogPath) {
        this.outLogPath = outLogPath;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getTransactionDeposit() {
        return transactionDeposit;
    }

    public void setTransactionDeposit(Integer transactionDeposit) {
        this.transactionDeposit = transactionDeposit;
    }
}
