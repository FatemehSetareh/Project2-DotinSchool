package util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class XmlParser extends DefaultHandler {
    String id;
    private String type;
    private String ip;
    private Integer port;
    private String outLog;

    public XmlParser() {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if(qName.equals("terminal")){
                id = attributes.getValue("id");
                type = attributes.getValue("type");
            }
            else if(qName.equals("server")){
                ip = attributes.getValue("ip");
                port = Integer.parseInt(attributes.getValue("type"));
            }else if (qName.equals("outLog")){
                outLog = attributes.getValue("path");
            }
            else if(qName.equals("transactions")){
                transactions = new Transactions();
            }
            else if(qName.equals("transaction")){
                transaction = new Transaction();
                transaction.setId(Integer.parseInt(attributes.getValue("id")));
                transaction.setType(attributes.getValue("type"));
                transaction.setAmount(Integer.parseInt(attributes.getValue("amount")));
                transaction.setDeposit(attributes.getValue("deposit"));

        }



    }


}
