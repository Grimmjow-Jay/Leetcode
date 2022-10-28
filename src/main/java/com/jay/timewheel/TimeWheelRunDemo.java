package com.jay.timewheel;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jay Yang
 * @date 2022/10/19
 */
public class TimeWheelRunDemo {

    public static void main(String[] args) {

        // 1. 实例化一个时间轮
        TimeWheelScheduler scheduler = new TimeWheelScheduler(500L, 10);

        // 2. 添加task
        AtomicLong a = new AtomicLong(System.currentTimeMillis());
        AtomicLong b = new AtomicLong(System.currentTimeMillis());
        AtomicLong c = new AtomicLong(System.currentTimeMillis());
        AtomicLong d = new AtomicLong(System.currentTimeMillis());
        scheduler.schedule(() -> System.out.println((System.currentTimeMillis() - a.getAndSet(System.currentTimeMillis())) + "_a"), new FixedPeriodTrigger(5000L));
//        scheduler.schedule(() -> System.out.println((System.currentTimeMillis() - b.getAndSet(System.currentTimeMillis())) + "_b"), new FixedPeriodTrigger(3000L));
//        scheduler.schedule(() -> System.out.println((System.currentTimeMillis() - c.getAndSet(System.currentTimeMillis())) + "_c"), new FixedPeriodTrigger(4000L));
//        scheduler.schedule(() -> System.out.println((System.currentTimeMillis() - d.getAndSet(System.currentTimeMillis())) + "_d"), new FixedPeriodTrigger(7000L));

        scheduler.start();

    }

}
