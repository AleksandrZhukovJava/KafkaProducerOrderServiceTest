package ru.zhukov.producer.infrastructure.order.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhukov.producer.domain.kafka.EventType;
import ru.zhukov.producer.infrastructure.kafka.topic.GetKafkaTopics;

import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final GetKafkaTopics getKafkaTopics;

    @GetMapping("/get/topic")
    public String getTopic(EventType eventType) {
        return getKafkaTopics.getTopic(eventType);
    }

    @GetMapping("/get/topic/all")
    public List<String> getTopic() {
        return EnumSet.allOf(EventType.class).stream()
                .map(getKafkaTopics::getTopic)
                .toList();

    }
}
