package ru.zhukov.client.operations.child;

import lombok.RequiredArgsConstructor;
import ru.zhukov.client.domain.annotation.UseCase;
import ru.zhukov.client.domain.client.Child;
import ru.zhukov.client.domain.parent.Parent;
import ru.zhukov.client.infrastructure.order.repo.ChildRepository;
import ru.zhukov.client.infrastructure.order.repo.ParentRepository;
import ru.zhukov.client.infrastructure.order.rest.dto.CreateChildRequest;

import java.util.List;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class AddChild {
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;

    public UUID execute(CreateChildRequest request) {
        List<Parent> parents = parentRepository.findAllById(request.getParentIds());

        Child child = Child.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .birthday(request.getBirthday())
                .parents(parents)
                .build();

        return childRepository.save(child).getId();
    }
}
