package com.jay.timewheel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jay Yang
 * @date 2022/10/24
 */
public class SimpleTriggerContext implements TriggerContext {

    private final Map<Trigger, Long> triggerLastExecutionTimestamp = new ConcurrentHashMap<>();

    @Override
    public long getLastExecutionTimestamp(Trigger trigger) {
        return triggerLastExecutionTimestamp.getOrDefault(trigger, -1L);
    }

    @Override
    public void updateLastExecutionTimestamp(Trigger trigger, long timestamp) {
        triggerLastExecutionTimestamp.put(trigger, timestamp);
    }

}
