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

import test.smok.database.AppDatabase;
import test.smok.logic.CellDataManagerCreator;
import test.smok.logic.DataToServerService;
import test.smok.utils.DatabaseFacade;
import test.smok.utils.DatabaseMake;

public class MainActivity extends AppCompatActivity{
    public static Context context;
    public static int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity.context=getApplicationContext();
        Intent intent = new Intent(this, DataToServerService.class);
        intent.putExtra("Creator", new CellDataManagerCreator());
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
        String ret = "";
        try{
            DatabaseMake databaseMake = new DatabaseMake(new DatabaseFacade());
            boolean k = databaseMake.databaseFacade.checkIfExsistsGSM(AppDatabase.getAppDatabase(this),"65491","52911","260","2");
            ret += Boolean.toString(k);
        }
        catch(Exception e){
            ret = "chuj kurwa";
        }
        textView.setText(ret);
    }

}
