package ru.zhukov.client.domain.core;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Person {
    @Column(name  = "first_name", nullable = false)
    private String firstName;
    @Column(name  = "last_name", nullable = false)
    private String lastName;
    @Column(name  = "middle_name", nullable = false)
    private String middleName;
    @Column(name  = "birthday", nullable = false)
    private Instant birthday;
}