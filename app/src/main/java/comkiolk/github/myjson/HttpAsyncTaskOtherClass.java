package comkiolk.github.myjson;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpAsyncTaskOtherClass extends AsyncTask<Pair<Context, String>, Void, String> {

    public static final String TAG = "asynTaskLog";
    HttpClient mHttpClient = new HttpClient();
    ParsFactory mParsFactory = new ParsFactory();
    Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "Start work onPreExecute");
    }

    @Override
    protected void onPostExecute(String pS) {
        super.onPostExecute(pS);
        Log.d(TAG, "Start work onPostExecute");
        //  Toast.makeText(mContext, pS, Toast.LENGTH_LONG).show();
        //Button btn = (Button)mContext.getClass()findViewById
        String name = "";
        MainActivity mainActivity = new MainActivity();
        try {
            JSONObject object = new JSONObject(pS);
            JSONArray information = object.getJSONArray("items");
            for(int i = 0; i < information.length(); ++i) {
                JSONObject writer = information.getJSONObject(i);
                name = writer.getString("name");
                String sentences = writer.getString("sentences");
                Toast.makeText(mContext, name, Toast.LENGTH_LONG).show();
                // mainActivity.mTextPresentation.setText(name);
                Toast.makeText(mContext, sentences, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException pE) {
            pE.printStackTrace();
        }
       // Toast.makeText(mContext, name, Toast.LENGTH_LONG).show();

    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        mContext = params[0].first;
        Log.d(TAG, "Start work doInBackground");
        String pString = params[0].second;
        String result = "";
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            InputStream in;

            try {
                url = new URL(pString);
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
