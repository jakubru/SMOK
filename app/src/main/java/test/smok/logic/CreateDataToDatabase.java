package test.smok.logic;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import test.smok.enity.GMS;

public class CreateDataToDatabase {


    public List<GMS> addGMSToDatabase(NodeList nList){
        List<GMS> gmsList=new ArrayList<>();
        GMS gms;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                gms=new GMS();
                gms.setPSC(Integer.parseInt(eElement.getElementsByTagName("PSC").item(0).getTextContent()));
                gms.setMNC(Integer.parseInt(eElement.getElementsByTagName("MNC").item(0).getTextContent()));
                gms.setBSIC(Integer.parseInt(eElement.getElementsByTagName("BSIC").item(0).getTextContent()));
                gms.setLONGITUDE(Float.parseFloat(eElement.getElementsByTagName("LONGITUDE").item(0).getTextContent()));
                gms.setMCC(Integer.parseInt(eElement.getElementsByTagName("MCC").item(0).getTextContent()));
                gms.setLATITUDE(Float.parseFloat(eElement.getElementsByTagName("LATITUDE").item(0).getTextContent()));
                gms.setCID(Integer.parseInt(eElement.getElementsByTagName("CID").item(0).getTextContent()));
                gms.setLAC(Integer.parseInt(eElement.getElementsByTagName("LAC").item(0).getTextContent()));
                gmsList.add(gms);
            }
        }
        return gmsList;
    }
}
