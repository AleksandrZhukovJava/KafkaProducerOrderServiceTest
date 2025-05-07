package ru.zhukov.producer.domain.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaOrder {
    private UUID aggregateId;
    public String customerName;
    public Instant orderDateCreated;
    public int itemAmount;
    @Builder.Default
    private Instant dateCreated = Instant.now();
}
