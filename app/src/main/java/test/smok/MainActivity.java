package test.smok;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import test.smok.logic.CDMACellDataCollector;
import test.smok.logic.Configuration;
import test.smok.logic.Functions;
import test.smok.logic.GPSDataCollector;
import test.smok.logic.GSMCellDataCollector;
import test.smok.logic.LTECellDataCollector;
import test.smok.logic.ReactionService;
import test.smok.logic.ReactionSubsystemCreator;
import test.smok.logic.WCDMACellDataCollector;

public class MainActivity extends AppCompatActivity{
    public static Context context;
    public static int i = 0;
    GPSDataCollector gps;
    GSMCellDataCollector g;
    LTECellDataCollector l;
    CDMACellDataCollector c;
    WCDMACellDataCollector w;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity.context=getApplicationContext();
        Intent intent = new Intent(this, ReactionService.class);
        intent.putExtra("Creator", new ReactionSubsystemCreator());
        /*Intent intent1 = new Intent(this, DataToServerService.class);
        intent1.putExtra("Creator", new CellDataManagerCreator());
        startService(intent1);*/
        gps = new GPSDataCollector(context);
        g = new GSMCellDataCollector(context);
        l = new LTECellDataCollector(context);
        c = new CDMACellDataCollector(context);
        w = new WCDMACellDataCollector(context);
        g.setNextCollector(l);
        l.setNextCollector(c);
        c.setNextCollector(w);
        startService(intent);
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
        String ret = "odleglosc" + Functions.checkArea(gps.getLat(), gps.getLong(), Configuration.getInstance(this).getLatitude(), Configuration.getInstance(this).getLongitude()) + "\n\n";
        ret += "CID " + g.getRegisteredCellInfo()[1] + "\n\n";
        ret += "wspolrzedne " + gps.collectData() + "\n\n";

        textView.setText(ret);
    }

}
