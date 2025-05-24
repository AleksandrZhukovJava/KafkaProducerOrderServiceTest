package ru.zhukov.client.infrastructure.order.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChildRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private Instant birthday;
    private List<UUID> parentIds;
}