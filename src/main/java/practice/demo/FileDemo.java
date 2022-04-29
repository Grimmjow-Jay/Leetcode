package practice.demo;

import java.io.*;

/**
 * @author Jay Yang
 * @date 2022/4/27
 */
public class FileDemo {

    public static void main(String[] args) throws IOException {

        File inFile = new File("C:\\Users\\jay\\Desktop\\temp\\test-zc-order-job.log");
        File outFile = new File("C:\\Users\\jay\\Desktop\\temp\\test-zc-order-job_1.log");

        try (FileReader fileReader = new FileReader(inFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(outFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains("WmsApiFactory") || line.startsWith("{\"address\"")) {
                    if (line.contains("\"sourceOrderNo\":\"SO22042701055401\"")) {
                        line = line.substring(700);
                    }
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }

            }
            bufferedWriter.flush();
        }
    }

}
