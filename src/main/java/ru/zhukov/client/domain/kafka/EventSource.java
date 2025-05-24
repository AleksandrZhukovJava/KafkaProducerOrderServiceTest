package ru.zhukov.client.domain.kafka;

import java.util.UUID;

public interface EventSource {
    UUID getAggregateId();

    EventType getEventType();
}
