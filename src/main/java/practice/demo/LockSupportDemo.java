package practice.demo;


import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/5/18
 */
public class LockSupportDemo {

    public static void main(String[] args) {

        System.out.println("start");

        LockSupport.parkUntil(System.currentTimeMillis() + 2000L);

        System.out.println("finished");

    }

}
