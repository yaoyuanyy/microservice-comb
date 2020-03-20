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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.annotation.Annotation;
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
            ClassPool pool = ClassPool.getDefault();
            //pool.insertClassPath(new LoaderClassPath(loader));
            CtClass cl = null;
            try {
                String realClassName = className.replaceAll("/", ".");
                cl = pool.get(realClassName);
                if(!cl.isAnnotation()
                        && !cl.isInterface()
                        && !cl.isPrimitive()
                        && !cl.isArray()
                        && !cl.isEnum()
                        && TargetAnnotations.hasTargetAnnotation(cl)){

                    CtField[] ctFields = cl.getDeclaredFields();
                    List<CtClass> ctClassOfFields = new ArrayList<>();
                    for (CtField ctField : ctFields) {
                        CtClass ctClassOfField = ctField.getType();
                        if(ctClassOfField.hasAnnotation(TargetAnnotations.FEIGN_CLIENT)) {
                            ctClassOfFields.add(ctClassOfField);
                        }
                    }

                    if(ctClassOfFields != null && !ctClassOfFields.isEmpty()){
                        CtMethod[] methods = cl.getDeclaredMethods();
                        for (CtMethod ctMethod : methods) {
                            if(!ctMethod.isEmpty()) {
                                handlerCtMethod(ctMethod, ctClassOfFields);
                            }
                        }
                    }
                }
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

    private void handlerCtMethod(CtMethod ctMethod, List<CtClass> classOfFields) throws CannotCompileException {
        //System.out.println("method:" + ctMethod.getLongName());
        ctMethod.instrument(new ExprEditor(){
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                for (CtClass classOfField : classOfFields) {
                    String classNameOfField = classOfField.getName();
                    if(classNameOfField.equals(m.getClassName())) {
                        try {
//                        System.out.println(" ctClassOfField:" + classNameOfField
//                                + " ctMethod:" + ctMethod.getName() + " m.getMethod():" + m.getMethod().getName());
                            for (String targetRequestAnnotation : TargetAnnotations.getTargetRequestAnnotations()) {
                                if(m.getMethod().hasAnnotation(targetRequestAnnotation)) {
                                    for (AttributeInfo attribute : m.getMethod().getMethodInfo().getAttributes()) {
                                        if(attribute instanceof AnnotationsAttribute) {
                                            AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute)attribute;
                                            Annotation annotation = annotationsAttribute.getAnnotation(targetRequestAnnotation);
                                            if(Objects.nonNull(annotation)) {
                                                String value = annotation.getMemberValue("value").toString();
                                                if(null != value && !"".equals(value)) {
                                                    String toApplication = null;
                                                    try {
                                                        Object[] objects = classOfField.getAnnotations();
                                                        for (Object object : objects) {
                                                            String s = object.toString();
                                                            if(s.contains("FeignClient")) {
                                                                toApplication = s;
                                                                break;
                                                            }
                                                        }
                                                    } catch (ClassNotFoundException e) {
                                                        e.printStackTrace();
                                                    }
                                                    map.put(classNameOfField, value);

                                                    System.out.print("调用方application: " + args + " 调用类: " +ctMethod.getDeclaringClass().getName()
                                                            +" \n调用方方法: " + ctMethod.getLongName()
                                                            +" \n被调用方application: " + toApplication + " 被调用方: " + classNameOfField
                                                            +" \n被调用方方法: " + m.getClassName() + "." + m.getMethodName() + " 对应路径: " + value +"\n");

                                                    System.out.println("-----------------");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (NotFoundException e) {
                            System.out.println("e:" + e);
                            e.printStackTrace();
                        }
                    }
                }

            }


        });
    }
    private void handleMethod(CtMethod method) {
    }
}
