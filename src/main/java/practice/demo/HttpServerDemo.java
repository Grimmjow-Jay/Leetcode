package practice.demo;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * @author Jay Yang
 * @date 2023/5/8
 */
public class HttpServerDemo {

    public static void main(String[] args) throws IOException {

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9991), 0);
        httpServer.createContext("/", httpExchange -> {
            URI requestURI = httpExchange.getRequestURI();
            System.out.println(requestURI);
            String response = "hello " + requestURI;
            httpExchange.sendResponseHeaders(200, response.length());
            httpExchange.getResponseBody().write(response.getBytes());
        });
        httpServer.start();

    }

}
