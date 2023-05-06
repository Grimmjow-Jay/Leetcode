package practice.demo;

import practice.util.urlclient.HttpClient;
import practice.util.urlclient.HttpMethod;
import practice.util.urlclient.Response;

import java.io.IOException;
import java.time.YearMonth;

/**
 * @author Jay Yang
 * @date 2023/5/6
 */
public class ReCalculateOutInSummaryDemo {

    public static void main(String[] args) throws IOException {

        String auth = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl9zeXN0ZW1fZGV2aWNlIjoiWlRfV0VCIiwidXNlcmlkIjo3NSwibG9naW5fdXNlcl9rZXkiOiI4OWNjOTM2YS1hODljLTQ5ZDMtODQyMi0xMDBlM2NkYmRjNGQifQ.9S8YvzL3h_xzKFmUgOs-hzKwzEHmL2MZ0Fqlzl5EgPZYg4gnDIyd5jXoJyREKfVHy5LvGq4k1OD3VrlPQLdQ-g";

        YearMonth start = YearMonth.parse("2021-08");
        YearMonth end = YearMonth.parse("2023-05");

        YearMonth yearMonth = start;
        while (!yearMonth.isAfter(end)) {

            HttpClient httpClient = HttpClient.open(HttpMethod.GET)
                    .url("https://erp.zhican.com/api/v1/finance/out-in-summary/generator?yearMonth=" + yearMonth)
                    .header("authorization", auth)
                    .header("client-id", "erp-client")
                    .header("granttype", "token");

            Response response = httpClient.execute();
            System.out.println(response);

            yearMonth = yearMonth.plusMonths(1);
        }

    }


}
