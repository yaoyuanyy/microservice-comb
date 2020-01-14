package com.skyler.cobweb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
        private List<String> brokers;
        private String topic;
        private String groupId;
    }

    @Data
    protected static final class Producer {
        private List<String> brokers;
        private String topic;

    }

}
