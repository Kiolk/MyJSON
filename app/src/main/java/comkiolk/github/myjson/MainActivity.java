package comkiolk.github.myjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mPersonName = new ArrayList<>();
    ArrayList<String> mPersonAge = new ArrayList<>();
    ArrayList<String> mPersonPhone = new ArrayList<>();

    private String mText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            final InputStream in = getAssets().open("example.txt");
            final int size = in.available();
            final byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            mText = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            final JSONObject object = new JSONObject(mText);
            final JSONArray arryPersons = object.getJSONArray("person");
            for (int i = 0; i < arryPersons.length(); ++i) {
                final JSONObject personDetail = arryPersons.getJSONObject(i);
                mPersonName.add(personDetail.getString("name"));
                mPersonAge.add(personDetail.getString("age"));
                mPersonPhone.add(personDetail.getString("phone"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        set();

    }

    public void set() {
        final ListView listOfPerson = (ListView) findViewById(R.id.persons_list_view);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), mPersonName, mPersonAge, mPersonPhone);
        listOfPerson.setAdapter(customAdapter);
    }
}
