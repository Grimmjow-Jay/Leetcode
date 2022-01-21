package practice.demo;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Jay Yang
 * @date 2021/12/31
 */
public class UrlConnectionDemo {

    public static void main(String[] args) throws IOException {

        URL url1 = new URL("http://localhost:9004/api/v1/basic/mainInfo/export/test");
        URL url2 = new URL("http://localhost:9106/basic/mainInfo/export/test");
        File dir1 = new File("C:\\Users\\jay\\Desktop\\temp\\export_download1");
        File dir2 = new File("C:\\Users\\jay\\Desktop\\temp\\export_download2");

        doDownload(url1, dir1);
        doDownload(url2, dir2);

    }

    private static void doDownload(URL url, File dir) throws IOException {
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            download(url, new File(dir, "export_" + i + ".txt"));
        }
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);
    }

    private static void download(URL url1, File file) throws IOException {

        URLConnection urlConnection = url1.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        byte[] buffer = new byte[1024];
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            int n;
            while (-1 != (n = inputStream.read(buffer))) {
                bufferedWriter.write(new String(buffer, 0, n));
            }
        }

    }

}
