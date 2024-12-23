package org.training.microservice.springplayground.java.kafka;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConsumerQueue {
    private BlockingQueue<String> messages = new ArrayBlockingQueue<>(100_000);

    public void addMessage(String messageParam){
        messages.add(messageParam);
    }

    public String getMessage() throws Exception {
        return messages.take();
    }

}
