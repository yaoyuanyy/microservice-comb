package com.skyler.cobweb.agent.getstarted;

import com.skyler.cobweb.agent.javassist.getstarted.Account;

import java.io.IOException;

/**
 * Description:
 * <p></p>
 * <pre>
 *
 *   NB. jvm需要加参数
 *   VM options: -javaagent:/Users/yaoliang/skyler/project/lianjia/microservice-comb/microservice-comb-javaagent/target/microservice-comb-javaagent-1.0.0-SNAPSHOT.jar=hello
 *
 *   参考：
 *   https://blog.csdn.net/xifeijian/article/details/79991913
 *   https://blog.csdn.net/wangzhongshun/article/details/100287986
 *   进阶
 *   https://paper.seebug.org/1099/#instrumentation_1
 *   http://www.throwable.club/2019/06/29/java-understand-instrument-first/#Instrumentation%E6%8E%A5%E5%8F%A3%E8%AF%A6%E8%A7%A3
 * </pre>
 * <p>
 * Created by skyler on 2020/3/9 at 5:50 PM
 */
public class AgentTest {
    public static void main(String[] args) throws IOException {

        Account account = new Account();
        account.operation("test11");

        System.out.println(" ------ main方法");

        Test t = new Test();
        // agentmain()方法的方式才需要以下代码
//        List<VirtualMachineDescriptor> virtualMachineDescriptors =  VirtualMachine.list();
//        for (VirtualMachineDescriptor virtualMachineDescriptor : virtualMachineDescriptors) {
//            System.out.println("virtualMachineDescriptor.id():" + virtualMachineDescriptor.id());
//            VirtualMachine virtualMachine = VirtualMachine.attach(virtualMachineDescriptor.id());
//            virtualMachine.loadAgent("");
//            virtualMachine.detach();
//        }
    }
}
