package ru.zhukov.producer.operations.order.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.zhukov.producer.domain.annotation.UseCase;
import ru.zhukov.producer.domain.kafka.KafkaOrder;
import ru.zhukov.producer.domain.order.Order;
import ru.zhukov.producer.infrastructure.kafka.PublishKafkaOrder;
import ru.zhukov.producer.infrastructure.order.mapper.OrderMapper;
import ru.zhukov.producer.infrastructure.order.repo.OrderRepository;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class PublishOrder {
    private final OrderRepository orderRepository;
    private final PublishKafkaOrder publishKafkaOrder;
    private final OrderMapper orderMapper;

    @Transactional
    public void execute(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("лень делать исключение, а ты криво набрал %s".formatted(id)));

        KafkaOrder kafkaOrder = orderMapper.toKafkaOrder(order);

        publishKafkaOrder.execute(kafkaOrder);
    }

}
