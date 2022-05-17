package practice.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jay Yang
 * @date 2022/5/17
 */
public class BatchHttpUrlConnectionDemo {

    private static final AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int threads = 20;
        for (int i = 0; i < threads; i++) {
            executorService.execute(() -> {
                while (true) {
                    try {
                        execute();
                        count.incrementAndGet();
                    } catch (IOException e) {
                        return;
                    }
                }
            });
        }

        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(() -> System.out.println(count.getAndSet(0)),
                        2000L,
                        2000L,
                        TimeUnit.MILLISECONDS);

    }

    private static void execute() throws IOException {
        URL url = new URL("http://localhost:7000/bootstrap/check");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n;
        while (-1 != (n = inputStream.read(buffer))) {
            byteArrayOutputStream.write(buffer, 0, n);
        }

        String result = byteArrayOutputStream.toString();
        if (!"{\"success\":true,\"code\":200,\"message\":\"SUCCESS\",\"data\":\"gateway\"}".equals(result)) {
            System.out.println(result);
        }

    }

}
