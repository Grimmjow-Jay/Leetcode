package xiaoxiami;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static xiaoxiami.SecretPassword.ENCRYPT_SIGN;
import static xiaoxiami.SecretPassword.PASSWORD;

/**
 * @author Grimm
 * @date 2021/8/20
 */
public class EncryptPicture {

    private static final String SOURCE_FILE_PATH = "C:\\Users\\Grimm\\Pictures\\腾讯微云图片\\xiaoxiami";

    public static void main(String[] args) throws IOException {

        File rootFile = new File(SOURCE_FILE_PATH);
        copyAndEncrypt(rootFile, rootFile.getParentFile());

    }

    private static void copyAndEncrypt(File source, File parentFile) throws IOException {
        if (source.isFile()) {
            File target = new File(parentFile, source.getName() + ENCRYPT_SIGN);
            Files.write(target.toPath(), PASSWORD.getBytes(), StandardOpenOption.CREATE_NEW);
            appendFile(source, target);
        } else if (source.isDirectory()) {
            File targetDir = new File(parentFile, source.getName() + ENCRYPT_SIGN);
            boolean dirCreated = targetDir.mkdir();
            if (!dirCreated) {
                throw new IOException("Create target directory failed!");
            }

            File[] children = source.listFiles();
            if (children != null) {
                for (File child : children) {
                    copyAndEncrypt(child, targetDir);
                }
            }
        }
    }

    private static void appendFile(File source, File target) throws IOException {
        try (FileWriter fileWriter = new FileWriter(target, true);
             FileReader reader = new FileReader(source)) {
            char[] buffer = new char[1024];
            int len;
            while ((len = reader.read(buffer)) != -1) {
                fileWriter.write(buffer, 0, len);
            }
        }
    }

}
