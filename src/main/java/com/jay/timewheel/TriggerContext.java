package com.jay.timewheel;

/**
 * @author Jay Yang
 * @date 2022/10/24
 */
public interface TriggerContext {

    /**
     * get last execution timestamp
     *
     * @param trigger Trigger
     * @return last execution timestamp, if trigger has never executed, return -1
     */
    long getLastExecutionTimestamp(Trigger trigger);

    /**
     * update last execution timestamp
     *
     * @param trigger   Trigger
     * @param timestamp last execution timestamp
     */
    void updateLastExecutionTimestamp(Trigger trigger, long timestamp);

}
