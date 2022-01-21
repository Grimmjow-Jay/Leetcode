package practice.xiaoxiami;

import java.io.*;

/**
 * @author Grimm
 * @date 2021/8/21
 */
public abstract class BaseSecretPicture {

    static final String PASSWORD = "The secret of my heart. ";

    static final String ENCRYPT_SIGN = "_encrypt";

    /**
     * operate file
     *
     * @param source source
     * @param target target
     * @throws IOException IOException
     */
    abstract void operateFile(File source, File target) throws IOException;

    /**
     * target file name
     *
     * @param sourceName source file name
     * @return target name
     */
    abstract String targetFileName(String sourceName);

    protected void doSecret(File source, File target) throws IOException {
        if (target.exists()) {
            throw new IOException("Target file or directory already exist!");
        }
        if (source.isFile()) {
            operateFile(source, target);
        } else if (source.isDirectory()) {
            boolean dirCreated = target.mkdir();
            if (!dirCreated) {
                throw new IOException("Create target directory failed!");
            }
            File[] children = source.listFiles();
            if (children != null) {
                for (File child : children) {
                    doSecret(child, new File(target, targetFileName(child.getName())));
                }
            }
        }
    }


}

class Encrypt extends BaseSecretPicture {

    public static void main(String[] args) throws IOException {

        File source = new File("C:\\Users\\Grimm\\Pictures\\腾讯微云图片\\xiaoxiami");
        File target = new File("C:\\Users\\Grimm\\Pictures\\腾讯微云图片\\xiaoxiami" + ENCRYPT_SIGN);
        new Encrypt().doSecret(source, target);

    }

    @Override
    void operateFile(File source, File target) throws IOException {
        try (InputStream inputStream = new FileInputStream(source);
             OutputStream outputStream = new FileOutputStream(target)) {

            outputStream.write(PASSWORD.getBytes());

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }
    }

    @Override
    String targetFileName(String sourceName) {
        return sourceName + ENCRYPT_SIGN;
    }
}

class Decrypt extends BaseSecretPicture {

    public static void main(String[] args) throws IOException {

        File source = new File("C:\\Users\\Grimm\\Pictures\\腾讯微云图片\\xiaoxiami" + ENCRYPT_SIGN);
        File target = new File("C:\\Users\\Grimm\\Pictures\\腾讯微云图片\\xiaoxiami");
        new Decrypt().doSecret(source, target);

    }

    @Override
    void operateFile(File source, File target) throws IOException {
        try (InputStream inputStream = new FileInputStream(source);
             OutputStream outputStream = new FileOutputStream(target)) {

            byte[] secretBytes = PASSWORD.getBytes();
            int secret = inputStream.read(secretBytes);
            if (secret < secretBytes.length) {
                throw new IOException("Can not decrypt.");
            }

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }
    }

    @Override
    String targetFileName(String sourceName) {
        return sourceName.substring(0, sourceName.length() - ENCRYPT_SIGN.length());
    }
}
