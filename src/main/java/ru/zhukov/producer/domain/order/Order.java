package ru.zhukov.producer.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.zhukov.producer.domain.kafka.EventSource;
import ru.zhukov.producer.domain.kafka.EventType;

import java.time.Instant;
import java.util.UUID;

import static ru.zhukov.producer.domain.kafka.EventType.ORDER;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order implements EventSource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String customerName;
    @CreationTimestamp
    private Instant dateCreated;
    private int itemAmount;

    @Override
    public UUID getAggregateId() {
        return id;
    }

    @Override
    public EventType getEventType() {
        return ORDER;
    }
}
