package util;

import main.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by ${Dotin} on ${4/25/2015}.
 */
public class XmlWriter {
    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    Document doc;

    public XmlWriter(String xmlFilePath) {
        this.docFactory = DocumentBuilderFactory.newInstance();
        try {
            this.docBuilder = docFactory.newDocumentBuilder();
            this.doc = docBuilder.parse(xmlFilePath);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateXml() {
        Node terminal = doc.getFirstChild();
        Node server = doc.getElementsByTagName("server").item(0);
        NamedNodeMap attr = server.getAttributes();

        Node ipAttr = attr.getNamedItem("ip");
        ipAttr.setTextContent(Main.ipAddress);

        Node portAttr = attr.getNamedItem("port");
        portAttr.setTextContent(Main.portNumber.toString());

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Main.xmlFilePath));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!Done!!!!!!!!!!!!!!!!!!!!");
    }

}
