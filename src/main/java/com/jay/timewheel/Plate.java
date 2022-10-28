package com.jay.timewheel;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Jay Yang
 * @date 2022/10/19
 */
public class Plate {

    private final long birthTimestamp;
    /**
     * 刻度数
     */
    private final int scaleCount;
    private final long oneCircleTime;
    private final TriggerContext triggerContext;
    private final Slot[] slots;

    /**
     * 最小刻度代表的时间毫秒数
     */
    private final long minScale;

    public Plate(long minScale, int scaleCount) {
        this.birthTimestamp = System.currentTimeMillis();
        this.scaleCount = scaleCount;
        this.minScale = minScale;
        this.oneCircleTime = scaleCount * minScale;
        this.triggerContext = new SimpleTriggerContext();
        this.slots = new Slot[scaleCount];
        for (int i = 0; i < scaleCount; i++) {
            slots[i] = new Slot();
        }
    }

    private int currentSlotIndex() {
        long distanceTime = System.currentTimeMillis() - birthTimestamp;
        return (int) ((distanceTime % oneCircleTime) / minScale);
    }

    public Slot currentSlot() {
        return slots[currentSlotIndex()];
    }

    public void insertSlot(ScheduleTask scheduleTask) {

        Trigger trigger = scheduleTask.getTrigger();
        long nextExecutionTimestamp = trigger.nextExecutionTimestamp(triggerContext);

        long distanceTime = nextExecutionTimestamp - System.currentTimeMillis();
        if (distanceTime < 0L) {
            return;
        }

        int circles = (int) (distanceTime / oneCircleTime);
        int slotCountFromNow = (int) ((distanceTime % oneCircleTime) / minScale);
        int slotIndex = currentSlotIndex() + slotCountFromNow;
        if (slotIndex >= scaleCount) {
            slotIndex = slotIndex - scaleCount;
        }

        Slot slot = slots[slotIndex];
        slot.addTask(scheduleTask, circles, nextExecutionTimestamp);

    }

    public void updateLastExecutionTimestamp(ScheduleTask scheduleTask) {
        Trigger trigger = scheduleTask.getTrigger();
        triggerContext.updateLastExecutionTimestamp(trigger, System.currentTimeMillis());
    }

    public void scaleJump() throws InterruptedException {

        long currentTimeMillis = System.currentTimeMillis();
        long nextSlotTimestamp = currentTimeMillis + minScale;
        nextSlotTimestamp = nextSlotTimestamp - (nextSlotTimestamp % minScale);

        LockSupport.parkUntil(nextSlotTimestamp);
    }

}
