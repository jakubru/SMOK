package test.smok;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import test.smok.logic.WCDMADataCollector;
import test.smok.logic.XMLDataParser;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity.context=getApplicationContext();
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
        WCDMADataCollector g = new WCDMADataCollector();
        TextView textView = (TextView) findViewById(R.id.SomeName);
        XMLDataParser xmlDataParser=new XMLDataParser(g);
        String [] tmp =
//                xmlDataParser.getDataCollectorArray();
                g.collect(this);
        xmlDataParser.parse(';',':');

        String tmp1 = "";
        for(int i = 0; i <tmp.length; i++ ){
            tmp1 += tmp[i];
        }
        textView.setText(tmp1);
    }
    public static Context getContext(){
        return MainActivity.context;
    }

}
