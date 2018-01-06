package test.smok.model;

/**
 * Created by matthew
 */
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name="ListNetworkData")
public class ListNetworkData {
    @ElementList(name="Data")
    private List<NetworkData> elements;

    public ListNetworkData() {
    }

    public ListNetworkData(List elements) {
        this.elements = elements;
    }

    public List<NetworkData> getElements() {
        return elements;
    }

    public void setElements(List<NetworkData> elements) {
        this.elements = elements;
    }
    public void setElement(NetworkData element) {
        elements.add(element);
    }
}
