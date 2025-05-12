package ru.zhukov.producer.infrastructure.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import ru.zhukov.kafkalibrary.core.KafkaEvent;
import ru.zhukov.producer.domain.kafka.Event;
import ru.zhukov.producer.domain.kafka.EventSource;
import ru.zhukov.producer.infrastructure.kafka.mapper.ExtractEvent;
import ru.zhukov.producer.infrastructure.kafka.topic.GetKafkaTopics;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PublishKafkaEvent<K extends KafkaEvent<?>, T extends EventSource> {
    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final GetKafkaTopics getKafkaTopics;
    private final Map<Class<K>, ExtractEvent<K, T>> extractEventComponents;

    public PublishKafkaEvent(KafkaTemplate<Object, Object> kafkaTemplate,
                             GetKafkaTopics getKafkaTopics,
                             List<ExtractEvent<K, T>> extractEvent) {
        this.kafkaTemplate = kafkaTemplate;
        this.getKafkaTopics = getKafkaTopics;
        this.extractEventComponents = extractEvent.stream()
                .collect(Collectors.toMap(ExtractEvent::getEventClass, Function.identity()));
    }

    public void execute(Class<K> eventClass, T eventSource) {
        ExtractEvent<K, T> extractEvent = extractEventComponents.get(eventClass);

        Event event = extractEvent.execute(eventSource);

        String topic = getKafkaTopics.getTopic(event.getEventType());

        ProducerRecord<Object, Object> producerRecord =
                new ProducerRecord<>(topic, event.getAggregateId(), event.getPayload());

        CompletableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(producerRecord);
        future.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                log.error("С заказом [{}] все пошло по пизде, ошибка: {}", event, throwable.getMessage(),
                        throwable);
            } else {
                log.info("Закинули заказ [{}], все заебись, результат: {}", event, sendResult);
            }
        });
    }
}