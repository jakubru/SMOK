package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public class XMLDataParser extends Parser {
    public XMLDataParser() {

    }
    @Override
    public void parse(String data,String delimiter,String path) {
        System.out.println("Data==================="+data);
        WriteToXML writeToXML=new WriteToXML(path);
        String x="";
        if(String.valueOf(delimiter.charAt(2)).equals("|"))
        x="\\|";
        String[] tmpDataCollectorString=data.split(x);
        String[] elementID = tmpDataCollectorString[1].split(String.valueOf(delimiter.charAt(1)));
        String[] nameID = elementID[0].split(delimiter.charAt(0)+"");
        String[] elementTime = tmpDataCollectorString[tmpDataCollectorString.length-1].split(String.valueOf(delimiter.charAt(1)));
        String[] Timestamp = elementTime[0].split(delimiter.charAt(0)+"");
        writeToXML.writeID(elementID,String.valueOf(delimiter.charAt(0)),Timestamp);
        System.out.println(nameID[0]+":"+nameID[1]+";");
        System.out.println(Timestamp[0]+":"+Timestamp[1]+"|");
        String dataWithoutID=data
                .replace(nameID[0]+":"+nameID[1]+";","")
                .replace(Timestamp[0]+":"+Timestamp[1]+"|","");
        tmpDataCollectorString=dataWithoutID.split(x);
        System.out.println(dataWithoutID);
        for (String stringElement : tmpDataCollectorString) {
            String [] element = stringElement.split(String.valueOf(delimiter.charAt(1)));
            writeToXML.write(element, String.valueOf(delimiter.charAt(0)));
        }
    }


}