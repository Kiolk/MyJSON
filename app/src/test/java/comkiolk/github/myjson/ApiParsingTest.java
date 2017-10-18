package comkiolk.github.myjson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ApiParsingTest {

    MockJSON mockJSON;
    HttpClient mHttpClient;
    MainActivity parserActivity;
    public static final String URL = "https://myjson-182914.appspot.com/_ah/api/myFavoriteWriterApi/v1/myFavoriteWriter";


    @Before
    public void setupTest() {
        mockJSON = new MockJSON();
        //mHttpClient = mock(HttpClient.class);
        //parserActivity = new MainActivity();
    }

    @Test
    public void checkingCorrectWorkOfHttpClient() throws IOException{
        mHttpClient = new HttpClient();
        InputStream in = mHttpClient.serverRequest("http://localhost:8080/_ah/api/myFavoriteWriterApi/v1/myFavoriteWriter");
        assertNotNull(in);
    }

//    @Test
//    public void checkingParsingFromHttpClient()throws IOException{
//        mHttpClient = new HttpClient();
//        InputStream in = mHttpClient.serverRequest("http://localhost:8080/_ah/api/myFavoriteWriterApi/v1/myFavoriteWriter");
//        String string = parserActivity.fromStreamToString(in);
//        parserActivity.readJson(string);
//        assertEquals("Wayne Gretzky", parserActivity.mPersonName.get(0));
//    }

//    @Test
//    public void checkingParsingFromHttpClient2() throws IOException{
//        mHttpClient = new HttpClient();
//        InputStream in = mHttpClient.serverRequest("https://myjson-182914.appspot.com/_ah/api/myFavoriteWriterApi/v1/myFavoriteWriter");
//        String string = parserActivity.fromStreamToString(in);
//        parserActivity.readJson(string);
//        assertEquals("Kolya", parserActivity.mPersonName.get(0));
//    }
    @Test
    public void  checkParsFactory() throws IOException{
        mHttpClient = new HttpClient();
        ParsFactory parsFactory = new ParsFactory();
        InputStream in = mHttpClient.serverRequest(URL);
        String text = parsFactory.readStream(in);
        assertNotNull(text);
    }
    @Test
    public void checkRead() throws IOException{
        mHttpClient = new HttpClient();
        String URL = "https://myjson-182914.appspot.com/_ah/api/myFavoriteWriterApi/v1/myFavoriteWriter";
        String text = mHttpClient.readReturnString(URL);
        assertNotNull(text);
    }
}
