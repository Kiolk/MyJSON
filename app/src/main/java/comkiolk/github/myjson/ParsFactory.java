package comkiolk.github.myjson;

import java.io.IOException;
import java.io.InputStream;

public class ParsFactory {

    public String readStream(InputStream pInputStream) throws  IOException{
//        try{
            int size = pInputStream.available();
            byte[] buff = new byte[size];
            pInputStream.read(buff);
            //pInputStream.close();
            String readText = new String(buff, "UTF-8");
            return readText;
//        }catch (IOException pE){
//            pE.printStackTrace();
//        }
//        return null;
    }

}
