package com.skyler.cobweb.agent.javassist;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020/3/15 at 11:56 上午
 */
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AccountTest {

    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
//        CtClass ctClass = classPool.makeClass("com.skyler.cobweb.agent.javassist.SubAccount");
//        ctClass.setSuperclass(classPool.get("com.skyler.cobweb.agent.javassist.Account"));
//        // 调用方法
//        ((Account) ctClass.toClass().newInstance()).operation("");
//
//
//        // 添加方法并调用
//        ctClass = classPool.makeClass("com.skyler.cobweb.agent.javassist.SubAccount2");
//        ctClass.setSuperclass(classPool.get("com.skyler.cobweb.agent.javassist.Account"));
//        ctClass.addMethod(CtNewMethod.make(
//                "public void operation(String value) { System.out.println(\"operation... from Sub\"); }", ctClass));
//        ((Account) ctClass.toClass().newInstance()).operation("");


        // 更改现有方法
        CtClass ctClass = classPool.get("com.skyler.cobweb.agent.javassist.Account");

        CtMethod method = ctClass.getDeclaredMethod("operation");

        // $1 表示函数入栈第一个参数
        method.insertBefore("{ System.out.println($1); }");
        MethodInfo methodInfo = method.getMethodInfo();
//        Object o = ctClass.toClass().newInstance();
//        Method operationMethod = o.getClass().getMethod("operation", String.class);
//        operationMethod.invoke(o, "111");
        ((Account)ctClass.toClass().newInstance()).operation("111");
    }
}
