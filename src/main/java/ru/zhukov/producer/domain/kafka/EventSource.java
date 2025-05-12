package ru.zhukov.producer.domain.kafka;

import java.util.UUID;

public interface EventSource {
    UUID getAggregateId();

    EventType getEventType();
}
