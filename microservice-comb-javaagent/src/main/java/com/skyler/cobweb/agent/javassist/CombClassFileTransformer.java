package com.skyler.cobweb.agent.javassist;

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
import java.security.ProtectionDomain;
import java.util.Map;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

/**
 * 处理指定类的指定方法
 */
public class CombClassFileTransformer implements ClassFileTransformer {

    private String args;

    public CombClassFileTransformer() {}

    public CombClassFileTransformer(String args) {
        this.args = args;
    }

    private static final String TARGET_DIR = "com/skyler/cobweb/";
    Map<String, String> map = Storage.map();

    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        if(className.startsWith(TARGET_DIR) && !className.contains("$$EnhancerBySpringCGLIB$$") && !className.contains("$$FastClassBySpringCGLIB$$")){
            System.out.println("className:" + className);
            ClassPool pool = ClassPool.getDefault();
            System.out.println("pool:" + pool.toString());

            pool.insertClassPath(new LoaderClassPath(loader));
            System.out.println("hou:" + pool.toString());

            CtClass cl = null;
            try {
                String realClassName = className.replaceAll("/", ".");
                System.out.println("启动参数args:" + args + " realClassName:" + realClassName);
                    cl = pool.get(realClassName);
                    if(!cl.isAnnotation()
                            && !cl.isInterface()
                            && !cl.isPrimitive()
                            && !cl.isArray()
                            && !cl.isEnum()
                            && TargetAnnotations.hasTargetAnnotation(cl)){

                        CtField[] ctFields = cl.getDeclaredFields();
                        for (CtField ctField : ctFields) {
                            CtClass ctClassOfField = ctField.getType();
                            if(ctClassOfField.hasAnnotation(TargetAnnotations.FEIGN_CLIENT)) {
                                System.out.println("ctClassOfField name:" + ctClassOfField.getName() + " ctField name:"+ctField.getName());
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
                        if(m.getMethod().hasAnnotation(TargetAnnotations.REQUEST_MAPPING)){
                            System.out.println("m.getMethod().getName():" + m.getMethod().getName());
                            for (AttributeInfo attribute : m.getMethod().getMethodInfo().getAttributes()) {
                                if(attribute instanceof AnnotationsAttribute) {
                                    AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute)attribute;
                                    String value = annotationsAttribute.getAnnotation(TargetAnnotations.REQUEST_MAPPING).getMemberValue("value").toString();
                                    map.put(classNameOfField, value);
                                    System.out.println("m.getMethod() annotation value:" + value);
                                }
                            }
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

    private void doMethod(CtBehavior method) throws NotFoundException, CannotCompileException {

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
