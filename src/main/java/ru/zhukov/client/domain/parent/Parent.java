package ru.zhukov.client.domain.parent;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.zhukov.client.domain.client.Child;
import ru.zhukov.client.domain.core.ParentType;
import ru.zhukov.client.domain.core.Person;
import ru.zhukov.client.domain.kafka.EventSource;
import ru.zhukov.client.domain.kafka.EventType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static ru.zhukov.client.domain.kafka.EventType.PARENT;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parent")
public class Parent extends Person implements EventSource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "parent_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ParentType parentType;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "parents", cascade = CascadeType.PERSIST)
    private List<Child> children;
    @CreationTimestamp
    private Instant dateCreated;

    @Override
    public UUID getAggregateId() {
        return id;
    }

    @Override
    public EventType getEventType() {
        return PARENT;
    }
}
