package com.skyler.cobweb.config;

import com.skyler.cobweb.config.kafka.MessageKafkaConfiguration;
import com.skyler.cobweb.config.kafka.MessageSender;
import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-06 at 19:07
 */
@Configuration
@Slf4j
@AutoConfigureBefore(MessageKafkaConfiguration.class)
public class CobwebFeignRibbonConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired(required = false) private List<AnnotatedParameterProcessor> parameterProcessors = new ArrayList<>();

    @Autowired private MessageSender messageSender;


    @Bean
    @ConditionalOnMissingBean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory, SpringClientFactory clientFactory) {
        log.info("applicationName:" + applicationName);

        LoadBalancerFeignClient feignClient = new LoadBalancerFeignClient(new Client.Default(null, null), cachingFactory,
                clientFactory);

        Client proxy = (Client)Proxy.newProxyInstance(feignClient.getClass().getClassLoader(),
                new Class[]{Client.class},
                new LoadBalancerFeignClientInvocationHandler(feignClient, messageSender, applicationName));

        return proxy;
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public Contract feignContract(ConversionService feignConversionService) {
//        log.info("abcd feignConversionService");
//        return new SpringMvcContract(this.parameterProcessors, feignConversionService);
//    }
}
