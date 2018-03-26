package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public class XMLDataParser extends Parser {//może przemianuj tą klasę tak aby było zaznaczone, ze ten Parser obsługuje zbieranie danych z sieci komórkowej, być może wypadałoby też delimitery w parametrze w metodzie parse przechowywać w stringu, aby była ich nieograniczona ilość
//    private String DataCollectorString;
    public XMLDataParser() {
        //DataCollector cellDataCollector
        //TODO DataCollectorArray = cellDataCollector.collectData(); trzeba korzystać z tej metody, nie z collect, poza tym w parametrze nie CellDataCollector tylko DataCollector, tam gdzie był
        // podział na nowe komórki tablicy w zwracanej tablice jest to zastąpione w zeracanym stringu Stringu przez |
//        DataCollectorArray = cellDataCollector.collectData();
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
