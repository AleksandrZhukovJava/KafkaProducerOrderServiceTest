package ru.zhukov.producer.operations.order.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.zhukov.kafkalibrary.order.OrderCreateEvent;
import ru.zhukov.producer.domain.annotation.UseCase;
import ru.zhukov.producer.domain.order.Order;
import ru.zhukov.producer.infrastructure.kafka.PublishKafkaEvent;
import ru.zhukov.producer.infrastructure.order.mapper.OrderMapper;
import ru.zhukov.producer.infrastructure.order.repo.OrderRepository;
import ru.zhukov.producer.infrastructure.order.rest.dto.CreateOrderRequest;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class AddOrder {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PublishKafkaEvent<OrderCreateEvent, Order> publishKafkaEvent;

    @Transactional
    public UUID execute(CreateOrderRequest request) {
        Order order = orderMapper.toEntity(request);

        orderRepository.saveAndFlush(order);

        publishKafkaEvent.execute(OrderCreateEvent.class, order);

        return order.getId();
    }
}
