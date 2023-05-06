package practice.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay Yang
 * @date 2023/5/6
 */
public class SearchMD5PhoneDemo {

    private static boolean finished = false;

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {

        // 15801234567
        String searchString = "09ac65c3fd279b6a5be5facbed0befd0";

        byte[] searchBytes = hexToBytes(searchString);
        String s = bytesToHex(searchBytes);
        System.out.println(s.equals(searchString));

        List<Thread> threadList = new ArrayList<>();
        for (int i = 13; i < 20; i++) {
            long start = i * 10_0000_0000L;
            long end = (i + 1) * 10_0000_0000L;
            Searcher searcher = new Searcher(start, end, searchBytes);
            searcher.setName("Searcher " + i + "x");
            threadList.add(searcher);
            searcher.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }
        System.out.println("finished");
    }

    private static boolean bytesEquals(byte[] bytes1, byte[] bytes2) {
        if (bytes1.length != bytes2.length) {
            return false;
        }

        for (int i = 0; i < bytes1.length; i++) {
            if (bytes1[i] != bytes2[i]) {
                return false;
            }
        }
        return true;
    }

    public static String bytesToHex(byte[] bytes) {

        StringBuilder md5str = new StringBuilder();
        // 把数组每一字节换成16进制连成md5字符串
        for (byte aByte : bytes) {
            int digital = aByte;

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString();
    }

    public static byte[] hexToBytes(String hex) {

        byte[] bytes = new byte[16];
        for (int i = 0; i < hex.length(); i += 2) {
            int b = Integer.parseUnsignedInt(hex.substring(i, i + 2), 16);
            if (b > 127) {
                b -= 256;
            }
            bytes[i / 2] = (byte) b;
        }
        return bytes;
    }

    private static class Searcher extends Thread {

        private final long start;
        private final long end;
        private final byte[] searchBytes;
        private final MessageDigest md5;

        public Searcher(long start, long end, byte[] searchBytes) {
            this.start = start;
            this.end = end;
            this.searchBytes = searchBytes;
            try {
                this.md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {

            int step = 500_0000;
            long l = start;
            int i = 0;
            long lastTime = System.currentTimeMillis();
            long lastL = l;
            while (!finished && l < end) {
                byte[] digest = md5.digest(Long.toString(l).getBytes());
                if (bytesEquals(searchBytes, digest)) {
                    System.out.println(Thread.currentThread().getName() + " find it: " + l);
                    finished = true;
                    return;
                }
                l++;
                i++;
                if (i >= step) {
                    long t = System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName() + " search " + lastL + "-" + l + " cost: " + (t - lastTime) + " ms.");
                    i = 0;
                    lastTime = t;
                    lastL = l;
                }
            }

            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }

}
