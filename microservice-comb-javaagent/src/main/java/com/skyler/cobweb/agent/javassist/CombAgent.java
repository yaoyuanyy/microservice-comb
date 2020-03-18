package com.skyler.cobweb.agent.javassist;

import java.lang.instrument.Instrumentation;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020/3/15 at 9:54 下午
 */
public class CombAgent {
     /**
     * This method is called before the application’s main-method is called,
     * when this agent is specified to the Java VM.
     **/
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Adding a PerfMonXformer instance to the JVM.");

        System.out.println("loadedClass.getName() length:" + inst.getAllLoadedClasses().length);

        inst.addTransformer(new CombClassFileTransformer(agentArgs));
    }
}