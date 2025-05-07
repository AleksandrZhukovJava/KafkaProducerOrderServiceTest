package ru.zhukov.producer.infrastructure.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhukov.producer.domain.order.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
