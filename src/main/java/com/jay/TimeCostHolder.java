package com.jay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Jay Yang
 * @date 2023/4/16
 */
public class TimeCostHolder {

    private static final ThreadLocal<TaskStack> threadLocalStopWatchTask = new ThreadLocal<>();

    public static void startPhase(String name) {
        TaskStack taskStack = threadLocalStopWatchTask.get();
        if (taskStack == null) {
            taskStack = new TaskStack();
            threadLocalStopWatchTask.set(taskStack);
        }
        taskStack.push(new Task(name));
    }

    public static void endPhase() {
        TaskStack taskStack = threadLocalStopWatchTask.get();
        if (taskStack == null || taskStack.isEmpty()) {
            throw new ErrorEndPhaseException();
        }

        taskStack.pop();
        if (taskStack.isEmpty()) {
            threadLocalStopWatchTask.remove();
            System.out.println(taskStack.prettyPrint());
        }
    }

    private static class TaskStack {

        private static final long ONE_MILL_TO_NANOS = 1000000L;
        private static final int DOT_LENGTH = 40;
        private static final String SOLID = ".";
        private static final String HOLLOW = " ";

        private final List<Task> tasks = new ArrayList<>();
        private final Stack<Task> taskStack = new Stack<>();

        private long stackStartTime = Long.MAX_VALUE;
        private long stackEndTime = Long.MIN_VALUE;

        public void push(Task task) {
            if (tasks.size() > 500) {
                throw new TaskStackOverFlowException();
            }
            int depth = taskStack.size();
            task.setDepth(depth);
            tasks.add(task);
            taskStack.add(task);

            this.stackStartTime = Math.min(this.stackStartTime, task.getStartTime());
        }

        public void pop() {
            if (taskStack.isEmpty()) {
                return;
            }
            Task task = taskStack.pop();
            if (task != null) {
                task.setEndTime(System.nanoTime());
                this.stackEndTime = Math.max(this.stackEndTime, task.getEndTime());
            }
        }

        public boolean isEmpty() {
            return taskStack.isEmpty();
        }

        public String prettyPrint() {

            StringBuilder builder = new StringBuilder();

            String waterfall = "waterfall";

            int waterFallBeginBlankLength = (DOT_LENGTH - waterfall.length()) / 2;
            int waterFallEndBlankLength = DOT_LENGTH - waterfall.length() - waterFallBeginBlankLength;
            String waterFallBeginBlank = repeat(waterFallBeginBlankLength, "-");
            String waterFallEndBlank = repeat(waterFallEndBlankLength, "-");
            builder.append("|");
            builder.append(waterFallBeginBlank);
            builder.append(waterfall);
            builder.append(waterFallEndBlank);
            builder.append("|");
            builder.append("-----start-----|------end------");
            builder.append("|");

            String cost = "cost";
            String totalMills = totalMills() + "ms";
            int costLength = Math.max(totalMills.length(), "--cost--".length());

            int costBeginBlankLength = (costLength - cost.length()) / 2;
            int costEndBlankLength = costLength - cost.length() - costBeginBlankLength;
            String costBeginBlank = repeat(costBeginBlankLength, "-");
            String costEndBlank = repeat(costEndBlankLength, "-");

            builder.append(costBeginBlank);
            builder.append(cost);
            builder.append(costEndBlank);
            builder.append("|");
            builder.append(waterFallEndBlank);
            builder.append("method");
            builder.append(waterFallBeginBlank);

            for (Task task : tasks) {

                builder.append(System.lineSeparator())
                        .append("|");

                int startIndex = position(task.getStartTime());
                String startBlank = repeat(startIndex, HOLLOW);
                builder.append(startBlank);

                int endIndex = position(task.getEndTime());
                String dots = repeat(endIndex - startIndex, SOLID);
                builder.append(dots);

                String endBlank = repeat(DOT_LENGTH - endIndex, HOLLOW);
                builder.append(endBlank);

                builder.append("|")
                        .append(task.getStartTime())
                        .append("|")
                        .append(task.getEndTime())
                        .append("|")
                        .append(toMills(task.getTimeNanos(), costLength))
                        .append("|");

                String linkLine = repeat(task.getDepth(), "  ");
                builder.append(linkLine);

                builder.append(task.getName());
            }

            return builder.toString();
        }

        private int position(long time) {
            int index = BigDecimal.valueOf(time - this.stackStartTime)
                    .divide(BigDecimal.valueOf(this.stackEndTime - this.stackStartTime), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(DOT_LENGTH))
                    .intValue();
            return Math.min(Math.max(index, 0), DOT_LENGTH);
        }

        private String totalMills() {
            return String.valueOf((this.stackEndTime - this.stackStartTime) / ONE_MILL_TO_NANOS);
        }

        private String toMills(long timeNanos, int totalLength) {
            String mills = timeNanos / ONE_MILL_TO_NANOS + "ms";
            return formatLength(mills, totalLength);
        }

        private String formatLength(String s, int totalLength) {
            int blankLength = totalLength - s.length();
            return repeat(blankLength, " ") + s;
        }

        private String repeat(int count, String s) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < count; i++) {
                builder.append(s);
            }
            return builder.toString();
        }

    }

    private static class Task {

        private final String name;
        private final long startTime;

        private int depth;
        private Long endTime;

        public Task(String name) {
            this.name = name;
            this.startTime = System.nanoTime();
        }

        public String getName() {
            return name;
        }

        public long getStartTime() {
            return startTime;
        }

        public Long getEndTime() {
            return endTime;
        }

        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public long getTimeNanos() {
            return endTime == null ? -1L : (endTime - startTime);
        }
    }

    private static class TaskStackOverFlowException extends RuntimeException {
    }

    private static class ErrorEndPhaseException extends RuntimeException {
    }

}
