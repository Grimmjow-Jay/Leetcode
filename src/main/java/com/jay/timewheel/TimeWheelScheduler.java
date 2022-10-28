package com.jay.timewheel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jay Yang
 * @date 2022/10/19
 */
public class TimeWheelScheduler {

    private final Plate plate;
    private final ExecutorService executor;

    public TimeWheelScheduler(long minScale, int scaleCount) {
        plate = new Plate(minScale, scaleCount);
        executor = Executors.newCachedThreadPool();
    }

    public void schedule(Runnable task, Trigger trigger) {
        plate.insertSlot(new ScheduleTask(task, trigger));
    }

    public void start() {

        while (true) {

            Slot currentSlot = plate.currentSlot();
            List<Slot.CircleTask> tasks = currentSlot.getTasks();

            List<Slot.CircleTask> removedCircleTask = new ArrayList<>();
            Iterator<Slot.CircleTask> circleTaskIterator = tasks.iterator();
            while (circleTaskIterator.hasNext()) {
                Slot.CircleTask circleTask = circleTaskIterator.next();
                if (circleTask.shouldRunTask()) {

                    ScheduleTask scheduleTask = circleTask.getTask();
                    executor.submit(scheduleTask::run);
                    plate.updateLastExecutionTimestamp(scheduleTask);

                    circleTaskIterator.remove();
                    removedCircleTask.add(circleTask);
                }

            }

            for (Slot.CircleTask circleTask : removedCircleTask) {
                plate.insertSlot(circleTask.getTask());
            }

            try {
                plate.scaleJump();
            } catch (InterruptedException e) {
                System.out.println("time wheel schedule finished.");
                return;
            }
        }
    }

}
