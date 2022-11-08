package practice.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * @author Jay Yang
 * @date 2022/11/1
 */
public class FileCopyDemo {

    public static void main(String[] args) throws IOException {
        String dir = "E:\\program data\\";
        String sourceFile = dir + "测试文件.txt";
        copy1(sourceFile, dir + "测试文件1.txt");
        copy2(sourceFile, dir + "测试文件2.txt");
        copy3(sourceFile, dir + "测试文件3.txt");
    }

    private static void copy1(String sourceFile, String targetFile) throws IOException {
        Files.deleteIfExists(new File(targetFile).toPath());
        long l1 = System.currentTimeMillis();
        byte[] cache = new byte[50 * 1024];

        try (FileInputStream in = new FileInputStream(sourceFile);
             FileOutputStream out = new FileOutputStream(targetFile)) {
            int read;
            while ((read = in.read(cache)) != -1) {
                out.write(cache, 0, read);
            }
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    private static void copy2(String sourceFile, String targetFile) throws IOException {
        Files.deleteIfExists(new File(targetFile).toPath());
        long l1 = System.currentTimeMillis();

        try (FileChannel readChannel = FileChannel.open(new File(sourceFile).toPath());
             FileChannel writeChannel = FileChannel.open(new File(targetFile).toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            readChannel.transferTo(0, readChannel.size(), writeChannel);
        }

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    private static void copy3(String sourceFile, String targetFile) throws IOException {
        Files.deleteIfExists(new File(targetFile).toPath());
        long l1 = System.currentTimeMillis();

        Files.copy(new File(sourceFile).toPath(), new File(targetFile).toPath());

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

}
