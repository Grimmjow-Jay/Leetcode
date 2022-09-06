package practice.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jay Yang
 * @date 2022/8/26
 */
public class MessageDetailDemo {

    public static void main(String[] args) throws IOException {
        String[] messages = {"AC1100060001685F4C2E83123FB7081A",
                "AC1100060001685F4C2E83121C090817",
                "AC1100060001685F4C2E831216560814",
                "AC1100060001685F4C2E831152E00811",
                "AC1100060001685F4C2E83114451080E",
                "AC1100060001685F4C2E83114250080B",
                "AC1100060001685F4C2E831141560808",
                "AC1100060001685F4C2E83113E520805",
                "AC1100060001685F4C2E83113C0C0802",
                "AC1100060001685F4C2E83113B7407FF",
                "AC1100060001685F4C2E83113B0A07FC",
                "AC1100060001685F4C2E831139BA07F9",
                "AC1100060001685F4C2E8311388407F6",
                "AC1100060001685F4C2E8311376E07F3",
                "AC1100060001685F4C2E831135CB07F0",
                "AC1100060001685F4C2E8311341707ED",
                "AC1100060001685F4C2E8311341607EB",
                "AC1100060001685F4C2E8311341507E9",
                "AC1100060001685F4C2E8311320307E6",
                "AC1100060001685F4C2E831131F907E4",
                "AC1100060001685F4C2E83112FE307E1",
                "AC1100060001685F4C2E83112FE207DF",
                "AC1100060001685F4C2E83112D1607DC",
                "AC1100060001685F4C2E83112D0A07D9",
                "AC1100060001685F4C2E83112BAF07D6"};

        List<String> bodies = new ArrayList<>();
        for (String message : messages) {
            String body = messageBody(message);
            System.out.println(body);
            bodies.add(body);
        }

        System.out.println(bodies.size());
        HashSet<String> strings = new HashSet<>(bodies);
        System.out.println(strings.size());

        Map<String, Long> collect = bodies.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        collect.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach(System.out::println);

    }

    private static String messageBody(String msgId) throws IOException {
        URL url = new URL("http://192.168.60.135:8076/message/viewMessage.query?msgId="+msgId+"&topic=WAREHOUSE_OUT_CONFIRM_TEST");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n;
        while (-1 != (n = inputStream.read(buffer))) {
            byteArrayOutputStream.write(buffer, 0, n);
        }

        String result = byteArrayOutputStream.toString();
        return result.substring(result.indexOf("messageBody"));
    }


}
