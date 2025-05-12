package ru.zhukov.producer.infrastructure.kafka.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.zhukov.producer.domain.kafka.EventType;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public final class GetKafkaTopics {
    private final Map<EventType, String> topicsByEventTypes;

    public GetKafkaTopics(RawKafkaTopics rawKafkaTopics) {
        this.topicsByEventTypes = mapKeyToEventType(rawKafkaTopics);

        log.info("Service topics - [{}]", topicsByEventTypes);
    }

    public String getTopic(EventType eventType) {
        String topic = topicsByEventTypes.get(eventType);

        if (topic == null) {
            throw new RuntimeException("Топик говна - [%s], и напиши уже исключения нормальные".formatted(eventType));
        }

        return topic;
    }

    private Map<EventType, String> mapKeyToEventType(RawKafkaTopics rawKafkaTopics) {
        return rawKafkaTopics.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> EventType.valueOf(e.getKey().toUpperCase()),
                        Map.Entry::getValue));
    }
}
