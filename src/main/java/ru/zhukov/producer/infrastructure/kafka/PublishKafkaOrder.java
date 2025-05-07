package ru.zhukov.producer.infrastructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import ru.zhukov.producer.domain.kafka.KafkaOrder;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublishKafkaOrder {
    @Value("${client.topics.order}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<UUID, String> kafkaTemplate;

    public void execute(KafkaOrder kafkaOrder) {
        String orderJson = serializeKafkaOrder(kafkaOrder);

        ProducerRecord<UUID, String> producerRecord =
                new ProducerRecord<>(orderTopic, kafkaOrder.getAggregateId(), orderJson);

        CompletableFuture<SendResult<UUID, String>> future = kafkaTemplate.send(producerRecord);
        future.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                log.error("С заказом [{}] все пошло по пизде, ошибка: {}", kafkaOrder, throwable.getMessage(),
                        throwable);
            } else {
                log.info("Закинули заказ [{}], все заебись, результат: {}", kafkaOrder, sendResult);
            }
        });
    }

    private String serializeKafkaOrder(KafkaOrder order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (Exception e) {
            throw new RuntimeException("Не серриализуется твое говно", e);
        }
    }
}