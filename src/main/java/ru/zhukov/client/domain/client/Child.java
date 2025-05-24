package ru.zhukov.client.domain.client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import ru.zhukov.client.domain.core.Person;
import ru.zhukov.client.domain.kafka.EventSource;
import ru.zhukov.client.domain.kafka.EventType;
import ru.zhukov.client.domain.parent.Parent;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static ru.zhukov.client.domain.kafka.EventType.CHILD;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "child")
public class Child extends Person implements EventSource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "parent_child",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    @EqualsAndHashCode.Exclude
    private List<Parent> parents;
    @CreationTimestamp
    private Instant dateCreated;

    @Override
    public UUID getAggregateId() {
        return id;
    }

    @Override
    public EventType getEventType() {
        return CHILD;
    }
}

