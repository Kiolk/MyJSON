package comkiolk.github.myjson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MockJSON {

    public String mText;
    ArrayList<String> mName = new ArrayList<>();
    ArrayList<String> mAbout = new ArrayList<>();
    ArrayList<Integer> mAge = new ArrayList<>();
    ArrayList<String> mRegistered = new ArrayList<>();

    public String stream(final InputStream pInputStream) {
        try {
            final InputStream in = pInputStream;
            final int size = in.available();
            final byte[] buffer = new byte[size];
            in.read(buffer);
            mText = new String(buffer, "UTF-8");
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mText;
    }

    public InputStream getInputStream(final String pFileName) {
        final InputStream pInputStream = MockJSON.class.getClassLoader().getResourceAsStream(pFileName); //delete part Short
        return pInputStream;
    }

    public void parsingOfJson (final String inputJsonString){
        try {
            final JSONArray listOfUsers = new JSONArray(inputJsonString);
            for(int i = 0; i < listOfUsers.length(); ++i){
                final JSONObject userDetails = listOfUsers.getJSONObject(i);
                mName.add(userDetails.getString("name"));
                mAbout.add(userDetails.getString("about"));
                mAge.add(userDetails.getInt("age"));
                mRegistered.add(userDetails.getString("registered"));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
