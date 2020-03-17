package com.skyler.cobweb.agent.javassist.shizhan;

import java.lang.instrument.ClassFileTransformer;
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
public class PerfMonAgent {
     /**
     * This method is called before the application’s main-method is called,
     * when this agent is specified to the Java VM.
     **/
    public static void premain(String agentArgs, Instrumentation _inst) {
        System.out.println("Adding a PerfMonXformer instance to the JVM.");
               // for (Class loadedClass : _inst.getAllLoadedClasses()) {
            System.out.println("loadedClass.getName() length:" + _inst.getAllLoadedClasses().length);
        //}
        _inst.addTransformer(new PerfMonXformer());
    }
}