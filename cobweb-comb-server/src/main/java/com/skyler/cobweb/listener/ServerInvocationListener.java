package com.skyler.cobweb.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyler.cobweb.mybatis.mapper.ServerInvocationMapper;
import com.skyler.cobweb.mybatis.model.ServerInvocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    @Autowired private ServerInvocationMapper mapper;


    @KafkaListener(topics = "${skyler.kafka.config.consumer.topic}", groupId = "${skyler.kafka.config.consumer.groupId}")
    public void accept(ConsumerRecord<Long, String> record){
        //
        log.info("accept data:{}", record.value());
        try {

            // 解析数据为对象
            ServerInvocation serverInvocation = objectMapper.readValue(record.value(), ServerInvocation.class);

            serverInvocation.setCreateName("");
            serverInvocation.setCreatorId(0L);
            serverInvocation.setCtime(new Date());
            serverInvocation.setUtime(new Date());
            serverInvocation.setMethod("");

            // 保存到储存介质中
            int modcount = mapper.insert(serverInvocation);
            log.info("insert db modcount:{}", modcount);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("ERROR:", e);
        }
    }
}
