package practice.demo;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jay Yang
 * @date 2023/3/1
 */
public class JolDemo {
    static Object generate() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", "b");
        map.put("c", new Date());
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        return map;
    }

    static void print(String message) {
        System.out.println(message);
        System.out.println("-------------------------");
    }

    public static void main(String[] args) throws IOException {
        Object obj = generate();
        //查看对象内部信息
        print(ClassLayout.parseInstance(obj).toPrintable());
        //查看对象外部信息
        GraphLayout instance = GraphLayout.parseInstance(obj);
        print(instance.toPrintable());
        //获取对象总大小
        print("size : " + instance.totalSize());
//        instance.toImage("C:\\Users\\jay\\Desktop\\temp\\int图像.png");
        System.out.println(instance.toFootprint());
    }
}
