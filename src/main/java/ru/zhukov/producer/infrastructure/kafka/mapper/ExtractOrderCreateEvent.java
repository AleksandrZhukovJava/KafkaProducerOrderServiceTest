package ru.zhukov.producer.infrastructure.kafka.mapper;

import org.springframework.stereotype.Component;
import ru.zhukov.kafkalibrary.core.KafkaEvent;
import ru.zhukov.kafkalibrary.order.OrderCreateEvent;
import ru.zhukov.producer.domain.order.Order;
import ru.zhukov.producer.infrastructure.kafka.topic.GetKafkaTopics;

@Component
public class ExtractOrderCreateEvent extends ExtractEvent<OrderCreateEvent, Order> {
    public ExtractOrderCreateEvent(GetKafkaTopics getKafkaTopics) {
        super(OrderCreateEvent.class, getKafkaTopics);
    }

    @Override
    protected KafkaEvent<?> getPayload(Order source) {
        return OrderCreateEvent.builder()
                .orderId(source.getId())
                .orderDateCreated(source.getDateCreated())
                .customerName(source.getCustomerName())
                .itemAmount(source.getItemAmount())
                .build();
    }

    @Override
    public Class<OrderCreateEvent> getEventClass() {
        return OrderCreateEvent.class;
    }
}
