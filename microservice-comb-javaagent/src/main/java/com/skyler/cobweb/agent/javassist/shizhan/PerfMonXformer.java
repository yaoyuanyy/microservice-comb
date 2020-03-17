package com.skyler.cobweb.agent.javassist.shizhan;

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

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

import com.skyler.cobweb.agent.rumen.AgentTest;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

public class PerfMonXformer implements ClassFileTransformer {
private static int i = 0;
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;

        if(className.contains("com/skyler/cobweb/a/web")){
            ClassPool pool = ClassPool.getDefault();
            CtClass cl = null;
            try {
                String realClassName = className.replaceAll("/", ".");
                System.out.println("realClassName:" + realClassName);
                //if(realClassName.contains("com.skyler.cobweb.a.web")){
                    cl = pool.get(realClassName);
                    if(!cl.isAnnotation()
                            && !cl.isInterface()
                            && !cl.isPrimitive()
                            && !cl.isArray()
                            && !cl.isEnum()){

                        CtField[] ctFields = cl.getDeclaredFields();
                        for (CtField ctField : ctFields) {
                            CtClass ctClassOfField = ctField.getType();
                            if(ctClassOfField.hasAnnotation("org.springframework.cloud.openfeign.FeignClient")) {
                                System.out.println("ctClassOfField name:" + ctClassOfField.getName() + "ctField name:"+ctField.getName());
                                CtMethod[] methods = cl.getDeclaredMethods();
                                for (CtMethod ctMethod : methods) {
                                    if(!ctMethod.isEmpty()) {
                                        handlerCtMethod(ctMethod, ctClassOfField.getName());
                                    }
                                }
                            }
                        }
                    }
                //}

//                cl = pool.makeClass(new java.io.ByteArrayInputStream(classfileBuffer));
//                if (cl.isInterface() == false) {
//                    CtBehavior[] methods = cl.getDeclaredBehaviors();
//                    for (int i = 0; i < methods.length; i++) {
//                        if (methods[i].isEmpty() == false) {
//                            doMethod(methods[i]);
//                        }
//                    }
//                    transformed = cl.toBytecode();
//                }
            } catch (Exception e) {
                System.err.println("Could not instrument  " + className
                        + ",  exception : " + e.getMessage());
            } finally {
                if (cl != null) {
                    cl.detach();
                }
            }
        }

        return classfileBuffer;
    }

    private void handlerCtMethod(CtMethod ctMethod, String classNameOfField) throws CannotCompileException {
        System.out.println("method:" + ctMethod.getLongName());
        ctMethod.instrument(new ExprEditor(){
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if(classNameOfField.equals(m.getClassName())) {
                    System.out.println("wo jiu yao ni -> m:" + m.getClassName() + "." + m.getMethodName());
                    try {
                        if(m.getMethod().hasAnnotation("org.springframework.web.bind.annotation.RequestMapping")){
                            System.out.println("m.getMethod().getName():" + m.getMethod().getName());
                            System.out.println("m.getMethod() annotation value:"+m.getMethod().getMethodInfo().getAttributes().get(1).toString());
                        }
                    } catch (NotFoundException e) {
                        System.out.println("e:" + e);
                        e.printStackTrace();
                    }
                    //
                }
            }
        });
    }

    private void doMethod(CtBehavior method) throws NotFoundException,
            CannotCompileException {
        //method.insertBefore("long stime = System.nanoTime();");
        System.out.println("method:" + method.getLongName());

        method.instrument(new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                System.out.println("m:" + m.getClassName() + "." + m.getMethodName());
//                m.replace("{ long stime = System.currentTimeMillis(); $_ = $proceed($$); " +
//                        "System.out.println(\"" + m.getClassName() + "." + m.getMethodName() + " stime=\" + stime);}");
             };

        });
    }

}
