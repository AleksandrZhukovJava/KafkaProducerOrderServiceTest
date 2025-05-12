package ru.zhukov.producer.infrastructure.kafka.topic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "client.topics")
public final class RawKafkaTopics extends HashMap<String, String> {
}
