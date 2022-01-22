package practice.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Jay Yang
 * @date 2022/1/22
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        run();

    }

    private static void run() throws InterruptedException, ExecutionException, TimeoutException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(3000L);
            return "ABC";
        });

        new Thread(futureTask).start();

        String s = futureTask.get(1000L, TimeUnit.MILLISECONDS);
        System.out.println(s);

    }

}
