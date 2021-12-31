package demo;

import sun.security.action.GetPropertyAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.AccessController;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Jay Yang
 * @date 2021/12/31
 */
public class CreateTestFile {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\jay\\Desktop\\temp\\export.txt");

        String separator = AccessController.doPrivileged(new GetPropertyAction("line.separator"));
        String line = IntStream.range(0, 17)
                .mapToObj(e -> "1234567890")
                .collect(Collectors.joining());
        line += separator;
        int count = 1000000;
        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (int i = 0; i < count; i++) {
                bufferedWriter.write(line);
            }
        }

    }

}
