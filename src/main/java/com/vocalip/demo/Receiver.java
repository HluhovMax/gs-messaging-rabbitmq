package com.vocalip.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(Map<String, String> human) {
        log.info("Received: <{}>", human);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
