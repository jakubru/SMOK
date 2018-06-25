package test.smok.logic;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by matthew on 13.01.18.
 */

public class WriteToXML {
    private String path;

    public WriteToXML(String path) {
        this.path = path;
    }

    public void write(String[] element, String delimiter) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            System.out.println("docBuild zrobione");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        File f = new File(path);
        System.out.println(f.getAbsolutePath());
        Document doc = null;
        Node rootElement;
        if (f.exists()) {
            // root elements
            try {
                doc = docBuilder.parse(f);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rootElement =
                    doc.getFirstChild();
        } else {
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("data");
            doc.appendChild(rootElement);
        }
//=================================
        String[] nameValue = element[0].split(delimiter);
        Element networkType = doc.createElement("params");
        Node device;

        device = doc.createElement("device");
        rootElement.appendChild(device);
        device.appendChild(networkType);
        Element parametr = null;


        for (String s : element) {
            nameValue = s.split(delimiter);
            parametr = doc.createElement("parametr");
            networkType.appendChild(parametr);

            // name elements
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(nameValue[0]));
            parametr.appendChild(name);

            // value elements
            Element value = doc.createElement("value");
            value.appendChild(doc.createTextNode(nameValue[1]));
            parametr.appendChild(value);
        }
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("File saved!");
//        Toast.makeText(MainActivity.getContext(),
//                "Done writing "+path,
//                Toast.LENGTH_SHORT).show();


    }

    public void writeID(String[] element, String delimiter, String [] Timestamp) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        File f = new File(path);
        Document doc = null;
        Node rootElement;
        Node deviceID;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        if (f.exists()) {
            try {
                doc = docBuilder.parse(f);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rootElement =
                    doc.getFirstChild();
        } else {
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("data");
            doc.appendChild(rootElement);
//=================================
            String[] nameID = element[0].split(delimiter);
            deviceID = doc.createElement("deviceID");
            rootElement.appendChild(deviceID);
            // name elements
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(nameID[0]));
            deviceID.appendChild(name);
            // value elements
            Element value = doc.createElement("value");
            value.appendChild(doc.createTextNode(nameID[1]));
            deviceID.appendChild(value);
            Element ts = doc.createElement(Timestamp[0]);
            ts.appendChild(doc.createTextNode(Timestamp[1]));
            deviceID.appendChild(ts);
        }
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("File saved!");
    }
}