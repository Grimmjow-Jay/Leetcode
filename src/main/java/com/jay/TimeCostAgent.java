package com.jay;

import java.lang.instrument.Instrumentation;

/**
 * @author Jay Yang
 * @date 2023/4/17
 */
public class TimeCostAgent {

    private final static String PACKAGE_PREFIX = "com.zc.order";

    public static void premain(String arg, Instrumentation instrumentation) {
        agentmain(arg, instrumentation);
    }

    public static void agentmain(String arg, Instrumentation instrumentation) {
        System.out.println("load time cost agent...");
        instrumentation.addTransformer(new TimeCostTransFormer(PACKAGE_PREFIX));

    }

}
