package com.jay.timewheel;

/**
 * @author Jay Yang
 * @date 2022/10/19
 */
public class FixedPeriodTrigger extends Trigger {

    private final long period;

    public FixedPeriodTrigger(long period) {
        if (period <= 0L) {
            throw new IllegalArgumentException("period must bigger than zero");
        }
        this.period = period;
    }

    @Override
    public long nextExecutionTimestamp(TriggerContext triggerContext) {
        long lastExecutionTimestamp = triggerContext.getLastExecutionTimestamp(this);
        return lastExecutionTimestamp <= 0
                ? (System.currentTimeMillis() + period)
                : (lastExecutionTimestamp + period);
    }

}
