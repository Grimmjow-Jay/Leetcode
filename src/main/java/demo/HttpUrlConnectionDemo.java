package demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Jay Yang
 * @date 2021/12/31
 */
public class HttpUrlConnectionDemo {

    public static void main(String[] args) throws IOException {

        URL url = new URL("http://localhost:9106/basic/mainInfo/pageList");
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("zc-user-id", "1");
        urlConnection.setRequestProperty("zc-user-name", "test");
        urlConnection.setDoOutput(true);

        urlConnection.getOutputStream().write("{}".getBytes());

        InputStream inputStream = urlConnection.getInputStream();
        byte[] buffer = new byte[1024];

        int n;
        while (-1 != (n = inputStream.read(buffer))) {
            System.out.write(buffer, 0, n);
        }

    }

}
