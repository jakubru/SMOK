package test.smok;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import test.smok.logic.GSMDataCollector;

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
        GSMDataCollector g = new GSMDataCollector(this);
        TextView textView = (TextView) findViewById(R.id.SomeName);
        String ret = "";
        try{
            String [] x = g.collect();
            for(int i = 0; i < x.length; i++){
                ret = ret + x[i];
            }
            ret = ret + "IMSI:" + g.getImsi() + ";Localization" + g.getLocalization();
        }
        catch(Exception e){
            ret = "klops kurwa";
        }
        textView.setText(ret);
    }
    public static Context getContext(){
        return MainActivity.context;
    }

}
