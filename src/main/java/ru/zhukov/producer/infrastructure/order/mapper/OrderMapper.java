package ru.zhukov.producer.infrastructure.order.mapper;

import org.springframework.stereotype.Component;
import ru.zhukov.producer.domain.order.Order;
import ru.zhukov.producer.infrastructure.order.rest.dto.CreateOrderRequest;

@Component
public class OrderMapper {
    public Order toEntity(CreateOrderRequest request) {
        return Order.builder()
                .customerName(request.getCustomerName())
                .itemAmount(request.getItemAmount())
                .build();
    }
}
