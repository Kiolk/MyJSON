package comkiolk.github.myjson;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import comkiolk.github.myjson.backend.myFavoriteWriterApi.MyFavoriteWriterApi;
import comkiolk.github.myjson.backend.myFavoriteWriterApi.model.MyFavoriteWriter;

public class MyWriterEndpointAysncTAskPost extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyFavoriteWriterApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyFavoriteWriterApi.Builder builder = new MyFavoriteWriterApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://myjson-182914.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String pId = params[0].second;
        Long index = Long.parseLong(pId);
        MyFavoriteWriter newWriter = new MyFavoriteWriter();
        newWriter.setId(index);
        newWriter.setName(params[1].second);
        newWriter.setSentences(params[2].second);

        try {
            return myApiService.insert(newWriter).execute().toString();      //get(index).execute().getName();   //sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Toast.makeText(context, "New quote added ", Toast.LENGTH_LONG).show();
    }
}
