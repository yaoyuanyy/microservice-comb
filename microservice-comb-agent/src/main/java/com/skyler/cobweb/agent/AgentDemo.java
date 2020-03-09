package com.skyler.cobweb.agent;

import java.lang.instrument.Instrumentation;

/**
 * Description:
 * <p></p>
 * <pre>
 *
 * </pre>
  * Created by skyler on 2020/3/9 at 5:47 PM
 */

public class AgentDemo {
    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========premain方法执行1========");
        System.out.println(agentArgs);
    }

    /**
     * 如果不存在 {@link AgentDemo#premain(String, Instrumentation)}, 则会执行本方法
     */
    public static void premain(String agentArgs) {
        System.out.println("=========premain方法执行2========");
        System.out.println(agentArgs);
    }
}
