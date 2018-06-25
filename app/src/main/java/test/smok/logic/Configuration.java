package test.smok.logic;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Kuba on 28.04.2018.
 */

public class Configuration {

    private static Configuration mInstance;
    private String mServerIP;
    private LinkedList<String> mAdditionalServers;
    private int mDataSendTime;
    private int mDatabaseCheckTime;
    private Context mContext;
    private double mLongitude;
    private double mLatitude;
    private double mRadius;
    private DataFromServerService dataFromServerService;
    protected TelephonyManager mTelephonyManager;
    private String PATH;



    public static Configuration getInstance(Context context){
        if (mInstance == null){
            try{
                mInstance = new Configuration(context);
            }
            catch(FileNotFoundException f){
                f.printStackTrace();
            }
        }
        return mInstance;
    }

    public void writeConfigToFile(String data){

        saveDataToFile(data,PATH);
    }

    private Configuration(Context context) throws FileNotFoundException {
        this.mContext = context;
        PATH=mContext.getFilesDir().getPath()+"/config.xml";
        dataFromServerService=new DataFromServerService();
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            writeConfigToFile(dataFromServerService.getDataFromServer("https://smok-prot-5.herokuapp.com/Configuration?want=conf&IMSI="+getIMSI()));
        }
        getDataConfigFromFile();
    }

    public int getDatabaseCheckTime(){
        return this.mDatabaseCheckTime;
    }

    public LinkedList<String> getListOfAdditionalServers(){
        return mAdditionalServers;
    }

    public String getServerIP(){
        return mServerIP;
    }

    public int getDataSendTime(){
        return mDataSendTime;
    }

    public double getLongitude(){
        return this.mLongitude;
    }

    public double getLatitude(){
        return this.mLatitude;
    }

    public double getRadius(){
        return this.mRadius;
    }

    private void getDataConfigFromFile(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);

        Document doc = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new FileInputStream(new File(PATH)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("user");
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                this.mDataSendTime = Integer.parseInt(eElement.getElementsByTagName("SEND_TIME").item(0).getTextContent());
                this.mDatabaseCheckTime = Integer.parseInt(eElement.getElementsByTagName("CHECK_DB_TIME").item(0).getTextContent());
                this.mLatitude = Double.parseDouble(eElement.getElementsByTagName("LATITUDE").item(0).getTextContent());
                this.mLongitude = Double.parseDouble(eElement.getElementsByTagName("LONGITUDE").item(0).getTextContent());
                this.mRadius = Double.parseDouble(eElement.getElementsByTagName("RADIUS").item(0).getTextContent());
            }
        }
    }
    private String getIMSI(){
        try{
            return this.mTelephonyManager.getSubscriberId();
        }
        catch(SecurityException e){
            return "";
        }
    }
    private void saveDataToFile(String data, String path) {
        File f = new File(path);
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(f));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
