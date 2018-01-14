package test.smok.logic;

import android.widget.Toast;

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

import test.smok.MainActivity;

/**
 * Created by matthew on 13.01.18.
 */

public class WriteToXML {
    public void write(String[] element, String delimiter){
        String path = "/sdcard/NetworkData.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        File f = new File(path);
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
            Toast.makeText(MainActivity.getContext(),
                    "Create new "+path,
                    Toast.LENGTH_SHORT).show();
        }
//=================================
        String[] nameValue=element[0].split(delimiter);
            Element networkType = doc.createElement(nameValue[1]);
            rootElement.appendChild(networkType);
            // parametr elements
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
        Toast.makeText(MainActivity.getContext(),
                "Done writing SD "+path,
                Toast.LENGTH_SHORT).show();


    }
}