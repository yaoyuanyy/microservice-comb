package com.skyler.cobweb.config.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Description: 发送消息
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-14 at 14:25
 */

@Component
@EnableConfigurationProperties(KafkaConfigProperties.class)
@Slf4j
public class MessageSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    private KafkaConfigProperties kafkaScheduleConfig;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 使用指定的topic发送消息方法
     *
     * @param o
     * @throws JsonProcessingException
     */
    public void send(Object o) throws JsonProcessingException {
        send(o, kafkaScheduleConfig.getProducer().getTopic());
    }

    /**
     * 通用发送消息方法
     *
     * @param o 消息体
     * @param topic
     * @throws JsonProcessingException
     */
    public void send(Object o, String topic) throws JsonProcessingException {
        if(StringUtils.isBlank(topic)) {
            throw new RuntimeException("topic must be not null or empty");
        }
        String data = objectMapper.writeValueAsString(o);
        kafkaTemplateString.send(topic, data);
        log.info("消息发送完成 data:{}", data);
    }
}
