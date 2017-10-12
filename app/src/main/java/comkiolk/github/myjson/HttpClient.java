package comkiolk.github.myjson;

import java.io.InputStream;

public class HttpClient {

    public InputStream serverRequest (String pUrl){
       InputStream url = HttpClient.class.getClassLoader().getResourceAsStream(pUrl);
       // throw new IllegalStateException("In Future must be implement this");
       // String url = pUrl;
        return url;
    }
}
