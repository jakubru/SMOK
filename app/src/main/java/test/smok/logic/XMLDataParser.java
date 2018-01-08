package test.smok.logic;

import test.smok.MainActivity;
import test.smok.model.NetworkData;

/**
 * Created by Kuba on 16.12.2017.
 */

public class XMLDataParser extends Parser {
    private String[] DataCollectorArray;


    public XMLDataParser(DataCollector dataCollector) {
        DataCollectorArray = dataCollector.collect(MainActivity.getContext());
        /* TODO
        Tutaj  anjlepiej odpalić kostruktor klasy bazowej, z tym, że nie wiem jak.*/
    }

    public String[] getDataCollectorArray() {
        return DataCollectorArray;
    }

    public void setDataCollectorArray(String[] dataCollectorArray) {
        DataCollectorArray = dataCollectorArray;
    }

    /*TODO
        Trzeba napisać parser danych przychodzących z GSMDataCollector, zapisać te dane do pliku xml i potem zapisać ten plik w jakimś folderze w telefonie.
        collectData zapisuje dane z wszystkich widocznych BTS-ów, dla sieci 3G u mnie w zasadzie widoczny jest tylko BTS, do którego jestem podłaczony, reszta wyrzuca wszędzie MAX_VALUE.
        Oddzielam paramettry i dane średnikiem i dwukropkiem, dlatego w metodzie są dwa delimitery.*/
    @Override
    public void parse(char delimiter1, char delimiter2) {
        String delimiterOne = delimiter1 + "";
        String delimiterTwo = delimiter2 + "";
        XMLSerialization xmlSerialization = new XMLSerialization();
        xmlSerialization.getElementsXML();
        NetworkData networkData = null;
        if(DataCollectorArray!=null) {
            for (String stringElement : DataCollectorArray) {
                String[] element = stringElement.split(delimiterOne);
                networkData = createNetworkData(element, delimiterTwo);
                xmlSerialization.addElement(networkData);
            }
        }
        xmlSerialization.writeElementsXML();
    }


    private NetworkData createNetworkData(String[] element, String delimiter) {
        NetworkData networkData = null;
        String[] nameValue;
        for (String s : element) {
            nameValue = s.split(delimiter);
            switch (nameValue[0]) {
                case "NetworkType":
                    networkData = new NetworkData();
                    networkData.setNetworkType(nameValue[1]);
                    break;
                case "CID":
                    networkData.setCID(nameValue[1]);
                    break;
                case "LAC":
                    networkData.setLAC(nameValue[1]);
                    break;
                case "ARFCN":
                    networkData.setARFCN(nameValue[1]);
                    break;
                case "MCC":
                    networkData.setMCC(nameValue[1]);
                    break;
                case "MNC":
                    networkData.setMNC(nameValue[1]);
                    break;
                case "PSC":
                    networkData.setPSC(nameValue[1]);
                    break;
                case "AsuLevel":
                    networkData.setAsuLevel(nameValue[1]);
                    break;
                case "DBM":
                    networkData.setDBM(nameValue[1]);
                    break;
                case "Level":
                    networkData.setLevel(nameValue[1]);
                    break;
                case "BSIC":
                    networkData.setBSIC(nameValue[1]);
                    break;
                case "TimingAdvance":
                    networkData.setTimingAdvance(nameValue[1]);
                    break;
                case "BasestationID":
                    networkData.setBasestationID(nameValue[1]);
                    break;
                case "RSRP":
                    networkData.setRSRP(nameValue[1]);
                    break;
                case "RSRQ":
                    networkData.setRSRQ(nameValue[1]);
                    break;
                case "RSSNR":
                    networkData.setRSSNR(nameValue[1]);
                    break;
                case "CQI":
                    networkData.setCQI(nameValue[1]);
                    break;
                case "PCI":
                    networkData.setPCI(nameValue[1]);
                    break;
                case "TAC":
                    networkData.setTAC(nameValue[1]);
                    break;
                case "Latitude":
                    networkData.setLatitude(nameValue[1]);
                    break;
                case "Longitude":
                    networkData.setLongitude(nameValue[1]);
                    break;
                case "NetworkID":
                    networkData.setNetworkID(nameValue[1]);
                    break;
                case "SystemID":
                    networkData.setSystemID(nameValue[1]);
                    break;
                case "CDMADBM":
                    networkData.setCDMADBM(nameValue[1]);
                    break;
                case "CDMAECIO":
                    networkData.setCDMAECIO(nameValue[1]);
                    break;
                case "CDMALevel":
                    networkData.setCDMALevel(nameValue[1]);
                    break;
                case "EVDODBM":
                    networkData.setEVDODBM(nameValue[1]);
                    break;
                case "EVDOECIO":
                    networkData.setEVDOECIO(nameValue[1]);
                    break;
                case "EVDOLevel":
                    networkData.setEVDOLevel(nameValue[1]);
                    break;
                case "EVDOSNR":
                    networkData.setEVDOSNR(nameValue[1]);
                    break;
                case "CI":
                    networkData.setCI(nameValue[1]);
                    break;
                case "EARFCN":
                    networkData.setEARFCN(nameValue[1]);
                    break;
                default:
                    break;
            }
        }
        return networkData;
    }
}
