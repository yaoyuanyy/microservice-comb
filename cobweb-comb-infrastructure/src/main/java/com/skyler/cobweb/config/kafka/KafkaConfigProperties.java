package com.skyler.cobweb.config.kafka;

import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-14 at 14:48
 */
@Data
@ConfigurationProperties("skyler.kafka.config")
public class KafkaConfigProperties {

    private final Consumer consumer = new Consumer();

    private final Producer producer = new Producer();

    @Data
    protected static final class Consumer {
        private String groupId;
        private List<String> brokers;
    }

    @Data
    protected static final class Producer {
        private String topic;
        private List<String> brokers;

    }

}
