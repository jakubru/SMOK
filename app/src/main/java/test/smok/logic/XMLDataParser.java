package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public class XMLDataParser extends Parser {
    public XMLDataParser() {

    }
    @Override
    public void parse(String data,String delimiter,String path) {
        WriteToXML writeToXML=new WriteToXML(path);
        String x="";
        if(String.valueOf(delimiter.charAt(2)).equals("|"))
        x="\\|";
        String[] tmpDataCollectorString=data.split(x);
        System.out.println(tmpDataCollectorString[0]);
        System.out.println(tmpDataCollectorString.length);
        for (String stringElement : tmpDataCollectorString) {
            String[] element = stringElement.split(String.valueOf(delimiter.charAt(1)));
            writeToXML.write(element, String.valueOf(delimiter.charAt(0)));
        }
    }


}
/*

 */
