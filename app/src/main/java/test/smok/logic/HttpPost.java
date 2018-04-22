package test.smok.logic;

/**
 * Created by matthew on 08.04.18.
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class HttpPost {
    URL u;
    private static CookieManager cm = new CookieManager();
    public int sendFile(String surl, String file1) {

        int rtn = 1;

        HttpURLConnection conn = null;
        BufferedReader br = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;

        InputStream is = null;
        OutputStream os = null;
        boolean ret = false;
        String StrMessage = "";
        String exsistingFileName = file1;
        File fFile2Snd = new File(exsistingFileName);

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "***232404jkg4220957934FW**";

        int bytesRead, bytesAvailable, bufferSize;

        byte[] buffer;

        int maxBufferSize = 1 * 1024 * 1024;

        String responseFromServer = "";

        String urlString = surl;
        try {
// ------------------ CLIENT REQUEST

            FileInputStream fileInputStream = new FileInputStream(new File(
                    exsistingFileName));
            rtn++;

// open a URL connection to the Servlet

            URL url = new URL(urlString);
            rtn++;

// Open a HTTP connection to the URL

            conn = (HttpURLConnection) url.openConnection();

// Allow Inputs
            conn.setDoInput(true);

// Allow Outputs
            conn.setDoOutput(true);

// Don't use a cached copy.
            conn.setUseCaches(false);

// Use a post method.
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"userfile\";"
                    + " filename=\"" + fFile2Snd.getName() + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            rtn++;

// create a buffer of maximum size

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

// read file and write it into form...

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

// send multipart form data necesssary after file data...

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

// close streams

            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            System.out.println("FromletCom2 CLIENT REQUEST:" + ex);
        }

        catch (IOException ioe) {
            System.out.println("FromletCom2 CLIENT REQUEST:" + ioe);
        }

// ------------------ read the SERVER RESPONSE

        try {
            System.out.println("Serveronse is: \n");
            inStream = new DataInputStream(conn.getInputStream());
            String str;
            while ((str = inStream.readLine()) != null) {
                System.out.println(str);
                System.out.println("");
            }
            inStream.close();
            System.out.println("\nENDer response ");

        } catch (IOException ioex) {
            System.out.println("FromverResponse): " + ioex);

        }
        rtn = 0;
        return rtn;

    }

}