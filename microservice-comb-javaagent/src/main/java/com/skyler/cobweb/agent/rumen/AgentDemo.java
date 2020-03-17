package com.skyler.cobweb.agent.rumen;

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
    /*
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("------ premain方法 有两个入参 ------ agentArgs:" + agentArgs + " inst:" + inst.toString());

//        for (Class loadedClass : inst.getAllLoadedClasses()) {
//            System.out.println("loadedClass.getName():" + loadedClass.getName());
//        }
//
//        inst.addTransformer(new ClassFileTransformer() {
//            @Override
//            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//                System.out.println("transform method className:" + className);
//                return new byte[0];
//            }
//        });
    }

    /**
     * 如果不存在 {@link AgentDemo#premain(String, Instrumentation)}, 则会执行本方法
     */
    public static void premain(String agentArgs) {
        System.out.println("------ premain方法，有一个入参 ------ agentArgs:" + agentArgs);
    }
}
