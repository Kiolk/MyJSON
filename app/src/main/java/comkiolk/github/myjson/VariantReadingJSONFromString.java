package comkiolk.github.myjson;

import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class VariantReadingJSONFromString extends AppCompatActivity {

    public String JSON_STRING_E = LoadJSONFromAsset();
//
//    = "{\n" +
//            "  \"person\": {\n" +
//            "    \"id\": \"1\",\n" +
//            "    \"name\": \"Keith\",\n" +
//            "    \"country\": \"Belarus\",\n" +
//            "    \"age\": \"30\",\n" +
//            "    \"sex\": \"M\"\n" +
//            "  }\n" +
//            "}";
//    String mNameOfPerson, mAgeOfPerson;
//    TextView mNameOfPersonTextView, mAgeOfPersonTextView;

//    @Override
//    protected void onCreate(final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mNameOfPersonTextView = (TextView) findViewById(R.id.name_of_person_text_view);
//        mAgeOfPersonTextView = (TextView) findViewById(R.id.age_of_person_text_view);
//
//        try {
//            final JSONObject object = new JSONObject(JSON_STRING_E);
//            final JSONObject person = object.getJSONObject("members");
//            mNameOfPerson = person.getString("name");
//            mAgeOfPerson = person.getString("age");
//            mNameOfPersonTextView.setText(mNameOfPerson);
//            mAgeOfPersonTextView.setText(mAgeOfPerson);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public String LoadJSONFromAsset()  {
        String jSSONFromFile = null;
    try {
        InputStream inter = this.getAssets().open("memb.json");//("membere.json");
        int size = inter.available();
        byte[] tmp = new byte[size];
        inter.read(tmp);
        inter.close();
        jSSONFromFile = new String(tmp, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
           return jSSONFromFile;
    }

}
