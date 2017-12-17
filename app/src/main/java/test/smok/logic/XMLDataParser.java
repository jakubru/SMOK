package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public class XMLDataParser extends Parser {

    public XMLDataParser(DataCollector dataCollector){
        /* TODO
        Tutaj  anjlepiej odpalić kostruktor klasy bazowej, z tym, że nie wiem jak.*/
    }
    /*TODO
    Trzeba napisać parser danych przychodzących z GSMDataCollector, zapisać te dane do pliku xml i potem zapisać ten plik w jakimś folderze w telefonie.
    collectData zapisuje dane z wszystkich widocznych BTS-ów, dla sieci 3G u mnie w zasadzie widoczny jest tylko BTS, do którego jestem podłaczony, reszta wyrzuca wszędzie MAX_VALUE.
    Oddzielam paramettry i dane średnikiem i dwukropkiem, dlatego w metodzie są dwa delimitery.*/
    @Override
    public void parse(char delimiter1, char delimiter2) {

    }
}
