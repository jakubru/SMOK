package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public class XMLDataParser extends Parser {
    private String[] DataCollectorArray;
    public XMLDataParser(CellDataCollector cellDataCollector) {
        //TODO DataCollectorArray = cellDataCollector.collectData(); trzeba korzystać z tej metody, nie z collect, poza tym w parametrze nie CellDataCollector tylko DataCollector, tam gdzie był
        // podział na nowe komórki tablicy w zwracanej tablice jest to zastąpione w Stringu przez |
    }

    public String[] getDataCollectorArray() {
        return DataCollectorArray;
    }

    public void setDataCollectorArray(String[] dataCollectorArray) {
        DataCollectorArray = dataCollectorArray;
    }

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
