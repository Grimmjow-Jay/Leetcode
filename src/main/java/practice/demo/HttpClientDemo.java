package practice.demo;

import practice.util.urlclient.HttpClient;
import practice.util.urlclient.HttpMethod;
import practice.util.urlclient.Response;

import java.io.IOException;

/**
 * @author Jay Yang
 * @date 2023/5/6
 */
public class HttpClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        for (int i = 0; i < 100; i++) {

            Thread.sleep(2000L);

            HttpClient httpClient = HttpClient.open(HttpMethod.GET);
            httpClient.url("https://erp.zhican.com/api/v1/finance/skuCostPriceLog/redo-calculate-desc")
                    .body("")
                    .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl9zeXN0ZW1fZGV2aWNlIjoiWlRfV0VCIiwidXNlcmlkIjo3NSwibG9naW5fdXNlcl9rZXkiOiI4OWNjOTM2YS1hODljLTQ5ZDMtODQyMi0xMDBlM2NkYmRjNGQifQ.9S8YvzL3h_xzKFmUgOs-hzKwzEHmL2MZ0Fqlzl5EgPZYg4gnDIyd5jXoJyREKfVHy5LvGq4k1OD3VrlPQLdQ-g")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("grantType", "token")
                    .header("Client-id", "erp-client");
            Response response = httpClient.execute();
            System.out.println(response.getBody());
        }

    }

}
