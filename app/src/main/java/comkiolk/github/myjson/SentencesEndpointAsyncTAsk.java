package comkiolk.github.myjson;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

class SentencesEndpointAsyncTAsk extends AsyncTask<String, String, String> {
    HttpClient pHttpClient = new HttpClient();
    MainActivity pActivity = new MainActivity();
    String text;
    @Override
    protected void onPostExecute(String pS) {
        super.onPostExecute(pS);
//  Toast.makeText(Context.this, "All normal", Toast.LENGTH_LONG).show();
        //pActivity.mStart.setText(pActivity.mPersonName.get(1));

    }

    @Override
    protected String doInBackground(String... pStrings) {
//      //  Toast.makeText(Context.this, "All normal", Toast.LENGTH_LONG).show();
//        try {
//            HttpClient mHttpClient = new HttpClient();
//            InputStream pIn = mHttpClient.serverRequest(pActivity.URL);
//            String pString = pActivity.fromStreamToString(pIn);
//            pActivity.readJson(pString);
//            //readJson(textJson);
//
//        }catch (IOException ex) {
//            ex.printStackTrace();
//        }
        return null;
    }
}
