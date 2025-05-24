package ru.zhukov.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.zhukov.client.infrastructure.kafka.topic.RawKafkaTopics;

@SpringBootApplication
@EnableConfigurationProperties(RawKafkaTopics.class)
public class ProducerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApplication.class, args);
    }

}
