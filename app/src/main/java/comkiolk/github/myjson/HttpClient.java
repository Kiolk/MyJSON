package comkiolk.github.myjson;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static final String TAG = "myTag";

    public InputStream serverRequest (String apiUrl) throws IOException{
      // InputStream url = HttpClient.class.getClassLoader().getResourceAsStream(pUrl);
       // throw new IllegalStateException("In Future must be implement this");
       // String url = pUrl;

        URL url;
        HttpURLConnection urlConnection = null;
        InputStream in;
//        try {
            url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = urlConnection.getInputStream();
            return in;
//        }catch (Exception pE){
//            pE.printStackTrace();
//        }
//                return null;
    }

    public  String serverRequestSecondVersion (String apiURL){
        InputStream pIn;
        try{
            URL url;
            HttpURLConnection httpURLConnection = null;
            try{
                url = new URL(apiURL);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                pIn = httpURLConnection.getInputStream();
                final int size = pIn.available();
                final byte[] buffer = new byte[size];
                pIn.read(buffer);
                //pIn.close();
                String jsonString = new String(buffer, "UTF-8");
                return jsonString;
                } catch (Exception ex){
                ex.printStackTrace();
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String readReturnString (String apiUrl)throws IOException{
        URL url;
        HttpURLConnection urlConnection = null;
        InputStream in;
        url = new URL(apiUrl);
        urlConnection = (HttpURLConnection) url.openConnection();
        in = urlConnection.getInputStream();
//        ParsFactory mParsFactory = new ParsFactory();
//        String text = mParsFactory.readStream(in);
        int size = in.available();
        byte[] buff = new byte[size];
        in.read(buff);
        //pInputStream.close();
        String readText = new String(buff, "UTF-8");

        return readText;
    }

    public String fromHttpToString (String apiURL){
               Log.d(TAG, "Start work fromHttpToString");
        String result = "";
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            InputStream in;

            try {
                url = new URL(apiURL);
                urlConnection = (HttpURLConnection) url.openConnection();
                in = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                int data = inputStreamReader.read();
                while (data != -1) {
                    result += (char) data;
                    data = inputStreamReader.read();
                }
                return result;
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        } catch (Exception pE) {
            pE.printStackTrace();
            return "Exeption" + pE.getMessage();
        }
        return result;
    }
}
