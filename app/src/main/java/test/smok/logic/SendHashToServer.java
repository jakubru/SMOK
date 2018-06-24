package test.smok.logic;

import android.arch.persistence.room.Database;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.smok.MainActivity;

/**
 * Created by matthew on 23.06.18.
 */

public class SendHashToServer {
    private final String urlHttp;
    private final Context context;
    protected TelephonyManager mTelephonyManager;

    public SendHashToServer(String urlHttp,Context context) {
        this.urlHttp=urlHttp;
        this.context=context;
        this.mTelephonyManager = (TelephonyManager) MainActivity.context.getSystemService(Context.TELEPHONY_SERVICE);

    }

    public void send() {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(new Runnable() {
            public void run() {
                URL url = null;
                try {
                    url = new URL("https://smok-prot-5.herokuapp.com/Configuration");
                    //?IMSI=260021711614296&hashes=GSM:1e213e60eb51b36c004179712b372d3;8
                    Map<String,Object> params = new LinkedHashMap<>();
                    params.put("IMSI", getIMSI());
                    DatabaseHash databaseHash=new DatabaseHash(context);
                    params.put("hashes", "GSM:"+databaseHash.hashGSM());



                    StringBuilder postData = new StringBuilder();
                    for (Map.Entry<String,Object> param : params.entrySet()) {
                        if (postData.length() != 0) postData.append('&');
                        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                        postData.append('=');
                        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                    }
                    byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(postDataBytes);

                    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String message="";
                    for (int c; (c = in.read()) >= 0;) {
                        message+=(char)c;
                        System.out.print((char) c);
                    }
                    System.out.println("========== "+message);
                    Handler handler = new Handler(Looper.getMainLooper());

                    final String finalMessage = message;
                    handler.post(new Runnable() {
                        public void run() {
                            if (finalMessage.equals("ok")){
                                Toast.makeText(context, "Dane są poprawne",
                                        Toast.LENGTH_LONG).show();
                                System.out.println("=====++===== "+ finalMessage);
                            }
                            if (finalMessage.equals("unknown user")){
                                Toast.makeText(context, "Brak usera w bazie",
                                        Toast.LENGTH_LONG).show();
                                System.out.println("===++======= "+ finalMessage);

                            }
                            if (finalMessage.equals("update")){
                                Toast.makeText(context, "Zaktualizuj bazę",
                                        Toast.LENGTH_LONG).show();
                                System.out.println("=++========= "+ finalMessage);
                            }
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.shutdown();
    }
    private String getIMSI() {
        try {
            return this.mTelephonyManager.getSubscriberId();
        } catch (SecurityException e) {
            return "";
        }
    }
}