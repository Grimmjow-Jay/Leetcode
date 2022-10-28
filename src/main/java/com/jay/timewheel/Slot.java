package com.jay.timewheel;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jay Yang
 * @date 2022/10/19
 */
public class Slot {

    @Getter
    private final List<CircleTask> tasks = new CopyOnWriteArrayList<>();

    public void addTask(ScheduleTask task, int circles, long executionTimestamp) {
        tasks.add(new CircleTask(task, circles, executionTimestamp));
    }

    @Getter
    public static class CircleTask {

        private final ScheduleTask task;
        private int circles;
        private final long executionTimestamp;

        public CircleTask(ScheduleTask task, int circles, long executionTimestamp) {
            this.circles = circles;
            this.task = task;
            this.executionTimestamp = executionTimestamp;
        }

        public boolean shouldRunTask() {
            return circles <= 0;
        }

        public void decreaseCircle() {
            circles--;
        }

    }

}
