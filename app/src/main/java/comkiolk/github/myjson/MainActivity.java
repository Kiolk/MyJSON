package comkiolk.github.myjson;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import comkiolk.github.myjson.backend.myFavoriteWriterApi.MyFavoriteWriterApi;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "myLog";
    ArrayList<Long> mIdNote = new ArrayList<>();
    ArrayList<String> mPersonName = new ArrayList<>();
    ArrayList<String> mSentences = new ArrayList<>();

    Button mStart;
    Button mNewPost;
    Button mUpdateButton;
    Button mFindByIdButton;
    Button mDeleteNote;
    EditText mIdEditText;
    EditText mNameEditText;
    EditText mSentencesEditText;
    TextView mTextPresentation;
    ProgressBar mProgressBar;

    public static final String URL = "https://myjson-182914.appspot.com/_ah/api/myFavoriteWriterApi/v1/myFavoriteWriter/";

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "createOnStart");
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNewPost = (Button) findViewById(R.id.post_new_quote_button);
        mTextPresentation = (TextView) findViewById(R.id.input_text_view);
        mStart = (Button) findViewById(R.id.start_button);
        mUpdateButton = (Button) findViewById(R.id.update_quote_button);
        mFindByIdButton = (Button) findViewById(R.id.find_quote_button);
        mDeleteNote = (Button) findViewById(R.id.delete_quote_button);
        mIdEditText = (EditText) findViewById(R.id.id_edit_text);
        mNameEditText = (EditText) findViewById(R.id.name_of_writer_edit_text);
        mSentencesEditText = (EditText) findViewById(R.id.sentences_edit_text);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        final  View.OnClickListener listenerClick = new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                final String  pId, pName, pSentences;
                switch (pView.getId()) {
                    case R.id.update_quote_button:
                        pId = mIdEditText.getText().toString();
                        pName = mNameEditText.getText().toString();
                        pSentences = mSentencesEditText.getText().toString();
                         new UpdateNoteAsyncTask().execute(new Pair<Context, String>(MainActivity.this, pId), new Pair<Context, String>(MainActivity.this, pName), new Pair<Context, String>(MainActivity.this, pSentences));
                        break;
                    case R.id.post_new_quote_button:
                        pId = mIdEditText.getText().toString();
                        pName = mNameEditText.getText().toString();
                        pSentences = mSentencesEditText.getText().toString();
                        new MyWriterEndpointAysncTAskPost().execute(new Pair<Context, String>(MainActivity.this, pId), new Pair<Context, String>(MainActivity.this, pName), new Pair<Context, String>(MainActivity.this, pSentences));
                        break;
                    case R.id.start_button:
                        new HttpAsyncTaskOtherClass().execute(new Pair<Context, String>(MainActivity.this, URL));
                        new getFromApiAsyncTask().execute(URL);
                        break;
                    case R.id.find_quote_button:
                        pId = mIdEditText.getText().toString();
                        new FindByIdAsyncTask().execute(new Pair<Context, String>(MainActivity.this, pId));
                        break;
                    case R.id.delete_quote_button:
                        pId = mIdEditText.getText().toString();
                        new DeleteNoteAsyncTask().execute(new Pair<Context, String>(MainActivity.this, pId));
                    default:
                        break;
                }
            }
        };
        mStart.setOnClickListener(listenerClick);
        mDeleteNote.setOnClickListener(listenerClick);
        mNewPost.setOnClickListener(listenerClick);
        mUpdateButton.setOnClickListener(listenerClick);
        mFindByIdButton.setOnClickListener(listenerClick);

    }

    public void set() {
        final ListView listOfPerson = (ListView) findViewById(R.id.persons_list_view);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), mPersonName, mSentences, mIdNote); //(getApplicationContext(), mPersonName, mPersonAge, mPersonPhone, mSentences);
        listOfPerson.setAdapter(customAdapter);
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {

        public static final String TAG = "asynTaskLog";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "Start work onPreExecute");
        }

        @Override
        protected void onPostExecute(String pS) {
            super.onPostExecute(pS);
            Log.d(TAG, "Start work onPostExecute");
            mTextPresentation.setText(pS);
        }

        @Override
        protected String doInBackground(String... pStrings) {
            Log.d(TAG, "Start work doInBackground");
            String pString = "My Text";
            return pString;

        }
    }

    public void requestPost() {

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Respons", response);
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("d", "Problems");
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "6");
                params.put("name", "Hahahahhahahhahahha");
                return params;
            }
        };

    }

    class getFromApiAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String pString) {
            super.onPostExecute(pString);
            mPersonName.clear();
            mSentences.clear();
            mIdNote.clear();
            String text = pString;
            Log.d(TAG, "Start work onPostExecute");
            try {
                JSONObject object = new JSONObject(text);
                JSONArray information = object.getJSONArray("items");
                for (int i = 0; i < information.length(); ++i) {
                    JSONObject writer = information.getJSONObject(i);
                    mPersonName.add(i, writer.getString("name"));
                    mSentences.add(i, writer.getString("sentences"));
                    mIdNote.add(i, writer.getLong("id"));
                }
            } catch (JSONException pE) {
                pE.printStackTrace();
            }
            mTextPresentation.setText(mPersonName.get(2));
            set();
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... pStrings) {
            String url = pStrings[0];
            HttpClient httpClient = new HttpClient();
            return httpClient.fromHttpToString(url);
        }
    }

    class FindByIdAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

        private MyFavoriteWriterApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if (myApiService == null) {  // Only do this once
                MyFavoriteWriterApi.Builder builder = new MyFavoriteWriterApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://myjson-182914.appspot.com/_ah/api/");

                myApiService = builder.build();
            }

            context = params[0].first;
            String pId = params[0].second;
            Long index = Long.parseLong(pId);

            try {
                return myApiService.get(index).execute().toString();      //get(index).execute().getName();   //sayHi(name).execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Toast.makeText(context, "Quote update added ", Toast.LENGTH_LONG).show();
            try {
                JSONObject object = new JSONObject(result);
                mPersonName.clear();
                mSentences.clear();
                mIdNote.clear();

                mPersonName.add(0, object.getString("name"));
                mSentences.add(0, object.getString("sentences"));
                mIdNote.add(0, object.getLong("id"));

            } catch (JSONException pE) {
                pE.printStackTrace();
            }

            set();
        }
    }

}
