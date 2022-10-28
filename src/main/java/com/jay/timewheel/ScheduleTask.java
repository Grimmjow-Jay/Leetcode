package com.jay.timewheel;

import lombok.Getter;

import java.util.Objects;

/**
 * @author Jay Yang
 * @date 2022/10/21
 */
@Getter
class ScheduleTask {

    private final Runnable task;
    private final Trigger trigger;

    public ScheduleTask(Runnable task, Trigger trigger) {
        this.task = Objects.requireNonNull(task);
        this.trigger = Objects.requireNonNull(trigger);
    }

    public void run() {
        task.run();
    }

}
