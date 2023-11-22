package practice.demo;

/**
 * @author Jay Yang
 * @date 2023/11/22
 */
public class StringFormatDemo {

    public static void main(String[] args) {
        String bucket = "yangdoudou-1258671337";
        String region = "ap-chengdu";
        String fileKey = "2023_11/20231122134655_example.jpg";
        String cosFilePath = String.format("https://%s.cos.%s.myqcloud.com/%s", bucket, region, fileKey);
        System.out.println(cosFilePath);
    }

}
