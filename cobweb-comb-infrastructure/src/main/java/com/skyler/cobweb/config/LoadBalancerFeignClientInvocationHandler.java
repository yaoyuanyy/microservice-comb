package com.skyler.cobweb.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyler.cobweb.config.kafka.MessageSender;
import com.skyler.cobweb.model.ServerInvocationInfo;
import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-06 at 19:47
 */
@Slf4j
public class LoadBalancerFeignClientInvocationHandler implements InvocationHandler {

    /**
     * 目标方法参数个数
     */
    private static final int ARGS_SIZE = 2;

    /**
     * 原始对象
     */
    private LoadBalancerFeignClient loadBalancerFeignClient;

    /**
     * 消息发射器
     */
    private MessageSender messageSender;

    /**
     * 服务应用名称 default: spring.application.name的值
     */
    private String applicationName;

    public LoadBalancerFeignClientInvocationHandler(LoadBalancerFeignClient loadBalancerFeignClient, MessageSender messageSender, String applicationName) {
        this.loadBalancerFeignClient = loadBalancerFeignClient;
        this.messageSender = messageSender;
        this.applicationName = applicationName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("FeignLoaderBalanceInvocationHandler invoke");
        this.handleArgs(args);
        return method.invoke(loadBalancerFeignClient, args);
    }

    private void handleArgs(Object[] args){
        try {
            if(Objects.nonNull(args) || args.length == ARGS_SIZE) {
                String fromPath = "";
                String toPath = "";
                String toApplication = "";

                if(args[0] instanceof Request){
                    Request request = (Request)args[0];
                    // todo 解析request.url()为toApplication and toPath
                    toApplication = request.url();
                    toPath = request.url();
                    log.info("FeignLoaderBalanceInvocationHandler to application and url:" + request.url());
                }

                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if(Objects.nonNull(attributes)){
                    HttpServletRequest request = attributes.getRequest();
                    if(Objects.nonNull(request)) {
                        fromPath = request.getRequestURI();
                        log.info("FeignLoaderBalanceInvocationHandler to application and url:" + request.getRequestURI());
                    }
                }

                // CQRS 发送kafka消息
                messageSender.send(ServerInvocationInfo.builder()
                        .fromApplication(applicationName)
                        .fromPath(fromPath)
                        .toApplication(toApplication)
                        .toPath(toPath)
                        .build());
            }
        } catch (Exception e) {
            log.warn("Exception:" + e);
            // todo
            // 发邮件F\
            // 发短信
        } finally {
            log.info("handleArgs 处理完成");
        }
    }
}
