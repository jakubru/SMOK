package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public class XMLDataParser extends Parser {
    private String[] DataCollectorArray;
    public XMLDataParser(DataCollector dataCollector) {
        DataCollectorArray = dataCollector.collect();
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
