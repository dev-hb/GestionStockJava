import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

    public static void main(String[] args) throws IOException {

        String url = "https://devcrawlers.com";
        URL url1 = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
        httpURLConnection.setRequestMethod("GET");
        if(httpURLConnection.getResponseCode() == 200){
            InputStream in;
            Reader reader = new InputStreamReader(httpURLConnection.getInputStream());
            System.out.println(reader.read());
        }
    }

}
