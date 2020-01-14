package com.skyler.cobweb.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyler.cobweb.config.KafkaConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-14 at 16:20
 */
@Slf4j
@Component
public class ServerInvocationListener {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${skyler.kafka.config.consumer.topic}",
            groupId = "${skyler.kafka.config.consumer.group-id}")
    public void accept(ConsumerRecord<Long, String> record){
        log.info("accept data:{}", record.value());
        //objectMapper.readValue()

        // 解析数据为对象

        // 保存到储存介质中
    }
}
