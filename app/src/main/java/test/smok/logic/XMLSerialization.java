package test.smok.logic;

import android.widget.Toast;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import test.smok.MainActivity;
import test.smok.model.ListNetworkData;
import test.smok.model.NetworkData;

/**
 * Created by matthew
 */

public class XMLSerialization {
    private ListNetworkData listNetworkData = null;
    private String path="/sdcard/NetworkData.xml";

    public XMLSerialization() {
    }

    public void getElementsXML() {
        InputStream inputStream = null;
        File xmlFile = new File(path);
        if (xmlFile.exists()) {
            try {
                inputStream = new BufferedInputStream(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Serializer serializer = new Persister();
                listNetworkData = serializer.read(ListNetworkData.class, inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.getContext(),
                        "Error",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.getContext(),
                    "Create new "+path,
                    Toast.LENGTH_SHORT).show();
            List ps = new ArrayList();
            listNetworkData = new ListNetworkData(ps);
        }
    }

    public void writeElementsXML() {
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Serializer serializer = new Persister();
            serializer.write(listNetworkData, outputStream);
            Toast.makeText(MainActivity.getContext(),
                    "Done writing SD "+path,
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.getContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void addElement(NetworkData networkData) {
        listNetworkData.setElement(networkData);
    }
}
