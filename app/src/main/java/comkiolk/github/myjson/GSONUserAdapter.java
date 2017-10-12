package comkiolk.github.myjson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GSONUserAdapter extends TypeAdapter {

    @Override
    public void write(final JsonWriter out, final Object value) throws IOException {

    }

    @Override
    public GsonUsers read(final JsonReader in) throws IOException {
        final GsonUsers pGsonUser = new GsonUsers();

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "name":
                    pGsonUser.name = in.nextString();
                    break;
                case "age":
                    pGsonUser.setAge(in.nextInt());
                    break;
                case "registered":
                    pGsonUser.registered = in.nextLong();
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();

        return pGsonUser;
    }

    public JsonReader creatJsonReaderObject(final String pathForFile) throws IOException {

        final InputStream pInputStream = GSONUserAdapter.class.getClassLoader().getResourceAsStream(pathForFile);
        final JsonReader pJsonReader = new JsonReader(new InputStreamReader(pInputStream));
        return pJsonReader;

    }

    public ArrayList<GsonUsers> readList(final JsonReader in) throws IOException {
        final ArrayList<GsonUsers> pGsonUserList = new ArrayList<>();
        in.beginArray();
        int i = 0;
        while (in.hasNext()) {
            switch (in.nextName()) {

                case "name":
                    pGsonUserList.get(i).name = in.nextString();
                    break;
                case "age":
                    pGsonUserList.get(i).setAge(in.nextInt());
                    break;
                case "registered":
                    pGsonUserList.get(i).registered = in.nextLong();
                    ++i;
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endArray();

        return pGsonUserList;
    }

    public String converterUnixTimeForHumanTime(final Long unixTime) {
        final Date date = new Date(unixTime);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");
        // sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        final String formateDate = sdf.format(date);
        // System.out.println(formateDate);
        return formateDate;
    }
}
