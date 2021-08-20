package xiaoxiami;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static xiaoxiami.SecretPassword.ENCRYPT_SIGN;
import static xiaoxiami.SecretPassword.PASSWORD;

/**
 * @author Grimm
 * @date 2021/8/20
 */
public class DecryptPicture {

    private static final String SOURCE_FILE_PATH = "C:\\Users\\Grimm\\Pictures\\腾讯微云图片\\xiaoxiami1_encrypt";

    public static void main(String[] args) throws IOException {
        File rootFile = new File(SOURCE_FILE_PATH);
        copyAndDecrypt(rootFile, rootFile.getParentFile());
    }

    private static void copyAndDecrypt(File source, File parentFile) throws IOException {
        String sourceFileName = source.getName();
        if (!sourceFileName.endsWith(ENCRYPT_SIGN)) {
            throw new IOException("Error file name. ");
        }
        String targetFileName = sourceFileName.substring(0, sourceFileName.length() - ENCRYPT_SIGN.length());
        if (source.isFile()) {
            decryptFile(source, new File(parentFile, targetFileName));
        } else if (source.isDirectory()) {
            File targetDir = new File(parentFile, targetFileName);
            boolean dirCreated = targetDir.mkdir();
            if (!dirCreated) {
                throw new IOException("Create target directory failed!");
            }

            File[] children = source.listFiles();
            if (children != null) {
                for (File child : children) {
                    copyAndDecrypt(child, targetDir);
                }
            }
        }
    }

    private static void decryptFile(File source, File target) throws IOException {
        try (FileWriter fileWriter = new FileWriter(target);
             FileReader reader = new FileReader(source)) {
            int secret = reader.read(new char[PASSWORD.length()]);
            if (secret < PASSWORD.length()) {
                throw new IOException("Can not decrypt.");
            }
            char[] buffer = new char[1024];
            int len;
            while ((len = reader.read(buffer)) != -1) {
                fileWriter.write(buffer, 0, len);
            }
        }
    }

}
