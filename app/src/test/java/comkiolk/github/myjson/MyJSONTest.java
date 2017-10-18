package comkiolk.github.myjson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MyJSONTest {

    public String mText;
    MockJSON mockJSON;
    HttpClient mHttpClient;

    @Before
    public void setupTest() {
        mockJSON = new MockJSON();
        mHttpClient = mock(HttpClient.class);
    }

    @Test
    public void checkingExtractionStringFromJSONFile() {
        try {
            final InputStream in = MyJSONTest.class.getClassLoader().getResourceAsStream("generated.json");
            final int size = in.available();
            final byte[] buffer = new byte[size];
            in.read(buffer);
            mText = new String(buffer, "UTF-8");
            in.close();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void checkingStringFromMockJSON() {
        final MockJSON example = new MockJSON();
        example.stream(example.getInputStream("generated.json"));
        assertNotNull(example.stream(example.getInputStream("generated.json")));
    }

    @Test
    public void checkForNullServerRequest() throws IOException {
        final HttpClient httpClient = new HttpClient();
        assertNull(httpClient.serverRequest("http://tut.by"));
    }

    //Checking parsing JSON file
    @Test
    public void checkParsingFromString() {
        mockJSON.stream(mockJSON.getInputStream("generated.json"));
        mockJSON.parsingOfJson(mockJSON.mText);
        assertEquals("Shelia Chang", mockJSON.mName.get(0));
        assertEquals((Integer) 26, mockJSON.mAge.get(2));
        assertEquals("1503282371100", mockJSON.mRegistered.get(3));
    }

    //Parsing JSON file from http client with mocked Object
    @Test
    public void checkParsingFroServerRequest() throws IOException{
        final InputStream correctInputStream = mockJSON.getInputStream("generated.json");
        when(mHttpClient.serverRequest("tut.by")).thenReturn(correctInputStream);
        mockJSON.stream(mHttpClient.serverRequest("tut.by"));
        mockJSON.parsingOfJson(mockJSON.mText);
        assertEquals("Shelia Chang", mockJSON.mName.get(0));
        //assertEquals("Sit quis officia velit consequat cillum nisi occaecat enim reprehenderit minim nostrud consequat duis. Fugiat reprehenderit amet sint cupidatat dolore proident et deserunt eiusmod aliqua veniam commodo cillum enim. Esse pariatur qui quis est sunt officia deserunt dolore magna dolore do magna. Exercitation nulla laborum culpa ut commodo ad eiusmod velit esse velit laboris incididunt. Magna pariatur excepteur deserunt et elit exercitation veniam anim incididunt esse adipisicing nulla. Fugiat nulla ipsum elit et anim in enim deserunt.\\r\\n\\n", mockJSON.mAbout.get(1));
        assertEquals((Integer) 26, mockJSON.mAge.get(2));
        assertEquals("1503282371100", mockJSON.mRegistered.get(3));
    }

    //this test checking correct transformation in object from JSON file with one object from file generatedShort.jsonVirgie Castro
    @Test
    public void getGsonUsersInfo() {
        final Gson gson = new Gson();
        final GsonUsers pGsonofUsers = gson.fromJson(mockJSON.stream(mockJSON.getInputStream("generatedShort.json")), GsonUsers.class);
        assertEquals("Shelia Chang", pGsonofUsers.name);
        assertEquals(23, pGsonofUsers.age);
        assertEquals("Frazier Tyler", pGsonofUsers.friends.get(0).name);
    }

    //Test check parsing information by GSON from arrayList
    @Test
    public void getArrayListGsonUserInfo() {
        final Gson gson = new Gson();
        final Type pGsonUserListType = new TypeToken<ArrayList<GsonUsers>>() {

        }.getType();
        final List<GsonUsers> pGsonUserList = gson.fromJson(mockJSON.stream(mockJSON.getInputStream("generated.json")), pGsonUserListType);  //(mockJSON.stream(mockJSON.getInputStream("generatedShort.json")), GsonUsers.class);
        assertEquals("Virgie Castro", pGsonUserList.get(2).friends.get(2).name);
        assertEquals("Freida Good", pGsonUserList.get(4).name);
        assertEquals("1502282371100", pGsonUserList.get(4).registered.toString());
    }

    //waste test
    @Test
    public void checkingGSONTypeAdapter() {
        JsonReader pJsonReader;
        try {
            final GSONUserAdapter gsonUserAdapter = new GSONUserAdapter();
            final GsonUsers pGsonUsers = gsonUserAdapter.read(new JsonReader(new InputStreamReader(mockJSON.getInputStream("E:\\Work\\Coding\\MyAndroidProjects\\MyJSON\\app\\src\\main\\resources\\generatedShort.json"), "UTF-8")));
            assertEquals(26, pGsonUsers.age);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //Test checking correct work GSON typeAdapter
    @Test
    public void checkingJsonAdapter() throws IOException {
        final GSONUserAdapter pGsonAdupter = new GSONUserAdapter();
        final JsonReader pJsonReader = pGsonAdupter.creatJsonReaderObject("generatedShort.json");
        final GsonUsers pGsonUser = pGsonAdupter.read(pJsonReader);
        assertEquals("Shelia Chang", pGsonUser.name);
        assertEquals("1507582371121", pGsonUser.registered.toString());
    }

    //Test checking method transformation unix time to normal result get from GSON typeAdapter
    @Test
    public void checkingConvertDate() throws IOException {
        final GSONUserAdapter pGsonAdupter = new GSONUserAdapter();
        final JsonReader pJsonReader = pGsonAdupter.creatJsonReaderObject("generatedShort.json");
        final GsonUsers pGsonUser = pGsonAdupter.read(pJsonReader);
        final String pDate = pGsonAdupter.converterUnixTimeForHumanTime(pGsonUser.registered);
        pGsonAdupter.converterUnixTimeForHumanTime(pGsonUser.registered);
        assertEquals("09 кст 2017, 22:52:51", pDate);
    }
//tets not work. Problems with GSON typeAdapter for arryList
    @Test
    public void checkingJsonAdapterFromList() throws IOException {
        final GSONUserAdapter pGsonAdupter = new GSONUserAdapter();
        final JsonReader pJsonReader = pGsonAdupter.creatJsonReaderObject("generated.json");
        final ArrayList<GsonUsers> pGsonUser = pGsonAdupter.readList(pJsonReader);
        assertEquals("Shelia Chang", pGsonUser.get(0).name);

    }
}
