package test.smok;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import test.smok.logic.CellDataCollector;
import test.smok.logic.CellDataManagerCreator;
import test.smok.logic.DataToServerService;
import test.smok.logic.GPSDataCollector;
import test.smok.logic.GSMCellDataCollector;
import test.smok.logic.ReactionService;
import test.smok.logic.ReactionSubsystemCreator;
import test.smok.logic.SendHashToServer;
import test.smok.logic.UpdateDatabase;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    public static int i = 0;
    private Button updateDatabase;
    private Button sendHashToServer;
    protected TelephonyManager mTelephonyManager;

    CellDataCollector cellDataCollector;
    GPSDataCollector gpsDataCollector;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity.context = getApplicationContext();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Intent intent = new Intent(this, ReactionService.class);
        intent.putExtra("Creator", new ReactionSubsystemCreator());
        Intent intent1 = new Intent(this, DataToServerService.class);
        intent1.putExtra("Creator", new CellDataManagerCreator());
        startService(intent1);
        startService(intent);
        gpsDataCollector = GPSDataCollector.getInstance(context);
        cellDataCollector = GSMCellDataCollector.getInstance(context);
        updateDatabase = (Button) findViewById(R.id.updateDatabaseButton);
        this.mTelephonyManager =(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        updateDatabase.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                UpdateDatabase updateDatabase=new UpdateDatabase();
                updateDatabase.updateDatabase();
            }
        });
        sendHashToServer = (Button) findViewById(R.id.sendHashToServerButton);
        sendHashToServer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SendHashToServer sendHashToServer=new SendHashToServer("https://smok-prot-5.herokuapp.com/Configuration",context);
                sendHashToServer.send();
                Toast.makeText(getApplicationContext(), "Wysyłam Hash do Serwera",
                        Toast.LENGTH_LONG).show();
            }
        });
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

    public void onRefreshButtonClick(View view) {
        TextView textView = (TextView) findViewById(R.id.SomeName);
        String ret = "CID " + cellDataCollector.getRegisteredCellInfo()[1] + "\n\n";
        ret += "wspolrzedne " + gpsDataCollector.getLat() + " " + gpsDataCollector.getLong();
        textView.setText(ret);
    }

    private String getIMSI() {
        try {
            return this.mTelephonyManager.getSubscriberId();
        } catch (SecurityException e) {
            return "";
        }
    }
}
