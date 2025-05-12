package ru.zhukov.producer.infrastructure.order.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhukov.producer.infrastructure.order.rest.dto.CreateOrderRequest;
import ru.zhukov.producer.infrastructure.order.rest.dto.Id;
import ru.zhukov.producer.operations.order.usecase.AddOrder;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final AddOrder addOrder;

    @PostMapping("/add")
    public Id<UUID> addOrder(@RequestBody CreateOrderRequest order) {
        return new Id<>(addOrder.execute(order));
    }
}
