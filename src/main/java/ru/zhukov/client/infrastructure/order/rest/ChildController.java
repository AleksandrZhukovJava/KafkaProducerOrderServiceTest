package ru.zhukov.client.infrastructure.order.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhukov.client.infrastructure.order.rest.dto.CreateChildRequest;
import ru.zhukov.client.infrastructure.order.rest.dto.Id;
import ru.zhukov.client.operations.child.AddChild;

import java.util.UUID;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
public class ChildController {
    private final AddChild addChild;

    @PostMapping("/add")
    public Id<UUID> addOrder(@RequestBody CreateChildRequest request) {
        return new Id<>(addChild.execute(request));
    }
}
