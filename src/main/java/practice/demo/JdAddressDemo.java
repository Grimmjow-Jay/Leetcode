package practice.demo;

import practice.util.urlclient.HttpClient;
import practice.util.urlclient.HttpMethod;

import java.io.IOException;

/**
 * @author Jay Yang
 * @date 2023/11/13
 */
public class JdAddressDemo {

    public static void main(String[] args) throws IOException {
        long id = 1L;
        String body = HttpClient.open(HttpMethod.GET)
                .url("https://fts.jd.com/area/get?fid=" + id)
                .execute()
                .getBody();
        System.out.println(body);
    }

}
