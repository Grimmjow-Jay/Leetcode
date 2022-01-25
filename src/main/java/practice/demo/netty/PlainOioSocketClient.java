package practice.demo.netty;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Jay Yang
 * @date 2022/1/25
 */
public class PlainOioSocketClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 4999));
        String hello = "hello";
        socket.getOutputStream()
                .write(hello.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = new byte[5 * 1024 * 1024];
        int read = socket.getInputStream().read(bytes);
        System.out.println(new String(bytes, 0, read, StandardCharsets.UTF_8));
        socket.close();
    }

}
