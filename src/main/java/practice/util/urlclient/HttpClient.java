package practice.util.urlclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;


/**
 * @author Jay Yang
 * @date 2023/5/6
 */
public class HttpClient {

    private final HttpMethod httpMethod;
    private final List<Header> headers = new ArrayList<>();

    private String url = "http://localhost:80";
    private String body;

    private HttpClient(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public static HttpClient open(HttpMethod httpMethod) {
        return new HttpClient(httpMethod);
    }

    public HttpClient url(String url) {
        this.url = url;
        return this;
    }

    public HttpClient header(String key, String value) {
        this.headers.add(new Header(key, value));
        return this;
    }

    public HttpClient body(String body) {
        if (HttpMethod.GET.equals(this.httpMethod)) {
            System.err.println("GET method not support body, will ignore");
        } else {
            this.body = body;
        }
        return this;
    }

    public Response execute() throws IOException {

        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        for (Header header : this.headers) {
            connection.setRequestProperty(header.getKey(), header.getValue());
        }

        connection.setRequestMethod(this.httpMethod.name());
        if (body != null && body.trim().length() > 0) {
            connection.setDoOutput(true);
            connection.getOutputStream().write(body.getBytes());
        }

        int responseCode = connection.getResponseCode();
        List<Header> responseHeaders = connection.getHeaderFields()
                .entrySet()
                .stream()
                .map(e -> new Header(e.getKey(), e.getValue() == null ? null : String.join(", ", e.getValue())))
                .collect(Collectors.toList());


        InputStream inputStream;
        if (responseCode < 400) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        StringJoiner body = new StringJoiner(System.lineSeparator());
        try (InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                body.add(line);
            }
        }

        return new Response(connection.getRequestMethod(), this.url, responseCode, responseHeaders, body.toString());
    }

}

