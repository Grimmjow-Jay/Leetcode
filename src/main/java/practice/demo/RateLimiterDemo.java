package practice.demo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jay Yang
 * @date 2022/12/5
 */
public class RateLimiterDemo {

    public static void main(String[] args) {

        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(60, 10);

        long startTime = System.currentTimeMillis();
        long i = 0L;

        while (System.currentTimeMillis() - startTime < (60 * 1000L)) {

            boolean acquired = rateLimiter.tryAcquire();
            if (acquired) {
                System.out.println(System.currentTimeMillis() + ": " + i);
            }
            i++;
        }
    }

    public static class TokenBucketRateLimiter {

        private final int maxSize;
        /**
         * 令牌生成速率
         */
        private final AtomicLong tokenBucket = new AtomicLong();
        private final double oneTokenMills;
        private long lastTimestamp;

        public TokenBucketRateLimiter(int maxSize, double limitPerSecond) {
            this.maxSize = maxSize;
            this.lastTimestamp = System.currentTimeMillis();
            this.oneTokenMills = 1000.0D / limitPerSecond;
        }

        public boolean tryAcquire() {

            putInToBucket();

            long token = tokenBucket.decrementAndGet();
            if (token >= 0) {
                return true;
            } else {
                tokenBucket.incrementAndGet();
                return false;
            }

        }

        private synchronized void putInToBucket() {

            long now = System.currentTimeMillis();
            double tokenNum = (now - lastTimestamp) / oneTokenMills;
            long delta = (long) tokenNum;
            if (delta <= 0) {
                return;
            }

            tokenBucket.addAndGet(delta);
            tokenBucket.set(Math.min(tokenBucket.get(), maxSize));

            lastTimestamp = now;
        }

    }
}
