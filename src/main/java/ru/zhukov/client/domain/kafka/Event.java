package ru.zhukov.client.domain.kafka;

import lombok.Builder;
import lombok.Data;
import ru.zhukov.kafkalibrary.core.KafkaEvent;

import java.time.Instant;
import java.util.UUID;

@Data
public class Event {
    private UUID aggregateId;
    private EventType eventType;
    private Instant sendDate;
    private String topic;
    private KafkaEvent<?> payload;

    @Builder
    public Event(EventType eventType, UUID aggregateId, KafkaEvent<?> payload, String topic) {
        this.eventType = eventType;
        this.aggregateId = aggregateId;
        this.payload = payload;
        this.topic = topic;
    }
}
