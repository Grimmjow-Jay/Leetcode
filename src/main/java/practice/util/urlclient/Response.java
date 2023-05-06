package practice.util.urlclient;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Response {

    private final String requestMethod;
    private final String requestUrl;
    private final int code;
    private final List<Header> headers;
    private final String body;

    Response(String requestMethod, String requestUrl, int code, List<Header> headers, String body) {
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.code = code;
        this.headers = Collections.unmodifiableList(new ArrayList<>(headers));
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(requestMethod.toUpperCase())
                .append(" ")
                .append(requestUrl)
                .append(System.lineSeparator())
                .append(System.lineSeparator());
        for (Header header : headers) {
            String key = header.getKey();
            String value = header.getValue();
            if (key != null) {
                builder.append(key)
                        .append(": ");
            }
            if (value != null) {
                builder.append(value);
            }
            builder.append(System.lineSeparator());
        }
        builder.append(System.lineSeparator())
                .append(body);
        return builder.toString();
    }
}
