import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Grimm
 * @date 2020/8/4
 */
public class GrimmTest {

    private static final AtomicLong CREATE_COUNT = new AtomicLong();
    private static final AtomicLong GC_COUNT = new AtomicLong();

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50);

    public static void main(String[] args) {
        executor.setMaximumPoolSize(50);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("CREATE_COUNT: " + CREATE_COUNT.getAndSet(0L));
                System.out.println("GC_COUNT: " + GC_COUNT.getAndSet(0L));
            }
        }).start();
        for (; ; ) {
            if (exec()) return;
        }
    }

    private static boolean exec() {
        T t = new T();
//        executor.scheduleWithFixedDelay(t::m, 2, 3, TimeUnit.SECONDS);
            executor.execute(t::m);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private static class T {
        public T() {
            CREATE_COUNT.incrementAndGet();
        }

        @Override
        protected void finalize() throws Throwable {
            GC_COUNT.incrementAndGet();
            super.finalize();
        }

        public void m() {
        }
    }
}