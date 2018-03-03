package test.smok.logic;

import test.smok.MainActivity;
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
        WriteToXML writeToXML=new WriteToXML();
        for (String stringElement : DataCollectorArray) {
            String[] element = stringElement.split(delimiterOne);
            writeToXML.write(element,delimiterTwo);
        }
    }


}
