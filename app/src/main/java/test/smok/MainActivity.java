package test.smok;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

import test.smok.logic.CDMACellDataCollector;
import test.smok.logic.CollectAndSend;
import test.smok.logic.DataManager;
import test.smok.logic.GPSDataCollector;
import test.smok.logic.GSMCellDataCollector;
import test.smok.logic.LTECellDataCollector;
import test.smok.logic.WCDMACellDataCollector;
import test.smok.logic.XMLDataParser;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    public static GPSDataCollector gps;
    public static LinkedList <String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity.context=getApplicationContext();
        gps = new GPSDataCollector(this);
        GSMCellDataCollector g = new GSMCellDataCollector(this);
        LTECellDataCollector l = new LTECellDataCollector(this);
        CDMACellDataCollector c = new CDMACellDataCollector(this);
        WCDMACellDataCollector w = new WCDMACellDataCollector(this);
        g.setNextCollector(l);
        l.setNextCollector(c);
        c.setNextCollector(w);
        list = new LinkedList<>();
        CollectAndSend collectAndSend = new CollectAndSend(list);
        collectAndSend.addDataManager(new DataManager(new XMLDataParser(),g));
        collectAndSend.addDataManager(new DataManager(new XMLDataParser(),gps));
        Thread thread = new Thread(collectAndSend);
        try{
            thread.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRefreshButtonClick(View view){
        TextView textView = (TextView) findViewById(R.id.SomeName);
        String ret;
        try{
            ret = list.getFirst();
            list.removeFirst();
            ret += list.getFirst();
            list.removeFirst();
        }
        catch(Exception e){
            ret = "za wczesnie";
        }
        textView.setText(ret);
    }
    public static Context getContext(){
        return MainActivity.context;
    }

}
