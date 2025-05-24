package ru.zhukov.client.infrastructure.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhukov.client.domain.parent.Parent;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {
}
