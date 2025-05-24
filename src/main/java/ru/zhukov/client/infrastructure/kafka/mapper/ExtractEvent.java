package ru.zhukov.client.infrastructure.kafka.mapper;

import ru.zhukov.kafkalibrary.core.KafkaEvent;
import ru.zhukov.client.domain.kafka.Event;
import ru.zhukov.client.domain.kafka.EventSource;
import ru.zhukov.client.infrastructure.kafka.topic.GetKafkaTopics;

public abstract class ExtractEvent<K extends KafkaEvent<?>, T extends EventSource> {
    protected final Class<K> eventClass;
    protected final GetKafkaTopics getKafkaTopics;

    public ExtractEvent(Class<K> eventClass, GetKafkaTopics getKafkaTopics) {
        this.eventClass = eventClass;
        this.getKafkaTopics = getKafkaTopics;
    }

    public Event execute(T source) {
        if (source == null) {
            throw new RuntimeException("Маппишь нулёк в евент... круто... напиши exception нормальный: [%s]");
        }

        return Event.builder()
                .aggregateId(source.getAggregateId())
                .eventType(source.getEventType())
                .payload(getPayload(source))
                .topic(getKafkaTopics.getTopic(source.getEventType()))
                .build();
    }

    protected abstract KafkaEvent<?> getPayload(T source);

    public abstract Class<K> getEventClass();
}
