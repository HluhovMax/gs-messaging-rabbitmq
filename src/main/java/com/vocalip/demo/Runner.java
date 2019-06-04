package com.vocalip.demo;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Sending message...");
        var name = "Max";
        Map<String, String> human = new HashMap<>();
        human.put("name", name);
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", human);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
