//package com.skyler.cobweb.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Description: kafka 配置相关
// * <pre>
// *
// * </pre>
// * NB.
// *
// * @author skyler
// * Created by on 2020-01-14 at 14:25
// */
//@Configuration
//@EnableConfigurationProperties(KafkaConfigProperties.class)
//public class MessageKafkaConfiguration {
//
//    private final KafkaConfigProperties kafkaConfigProperties;
//
//    public MessageKafkaConfiguration(KafkaConfigProperties kafkaConfigProperties) {
//        this.kafkaConfigProperties = kafkaConfigProperties;
//    }
//
//    @Bean("kafkaListenerContainerFactory")
//    public KafkaListenerContainerFactory kafkaListenerContainerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getConsumer().getBrokers());
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProperties.getConsumer().getGroupId());
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(configProps));
//        factory.setConcurrency(5);
//        factory.getContainerProperties().setPollTimeout(5000);
//
//        return factory;
//    }
//
//}
