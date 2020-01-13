package com.skyler.cobweb.config;

import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
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
     * kafka 生产者
     */
    public LoadBalancerFeignClientInvocationHandler(LoadBalancerFeignClient loadBalancerFeignClient) {
        this.loadBalancerFeignClient = loadBalancerFeignClient;
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
                if(args[0] instanceof Request){
                    Request request = (Request)args[0];
                    log.info("FeignLoaderBalanceInvocationHandler to application and url:" + request.url());
                    // 解析request.url()为toApplication and toPath
                }
                if(args[1] instanceof Request.Options){
                    Request.Options options = (Request.Options)args[1];
                    log.info("FeignLoaderBalanceInvocationHandler options.readTimeoutMillis():" + options.readTimeoutMillis());
                }

                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if(Objects.nonNull(attributes)){
                    HttpServletRequest request = attributes.getRequest();
                    if(Objects.nonNull(request)) {
                        log.info("FeignLoaderBalanceInvocationHandler to application and url:" + request.getRequestURI());
                    }
                }
            }
            // CQRS 发送kafka消息
        } catch (Exception e) {
            log.warn("Exception:" + e);
            // 发邮件
            // 发短信
        } finally {
            log.info("handleArgs 处理完成");
        }
    }
}
