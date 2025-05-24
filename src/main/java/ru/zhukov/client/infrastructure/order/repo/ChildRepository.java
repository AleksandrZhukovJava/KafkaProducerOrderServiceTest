package ru.zhukov.client.infrastructure.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhukov.client.domain.client.Child;

import java.util.UUID;

public interface ChildRepository extends JpaRepository<Child, UUID> {
}
