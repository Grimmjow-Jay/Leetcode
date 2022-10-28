package com.jay.timewheel;

/**
 * @author Jay Yang
 * @date 2022/10/19
 */
public abstract class Trigger {

    /**
     * next execution timestamp
     *
     * @param triggerContext TriggerContext
     * @return trigger next execution time
     */
    abstract long nextExecutionTimestamp(TriggerContext triggerContext);

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

}
