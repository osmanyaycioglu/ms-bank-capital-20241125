package org.training.microservice.springplayground.java.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerThread extends Thread {
    private static final Logger        logger = LoggerFactory.getLogger(ConsumerThread.class);
    private              ConsumerQueue consumerQueue;

    public ConsumerThread(final ConsumerQueue consumerQueueParam) {
        consumerQueue = consumerQueueParam;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String messageLoc = consumerQueue.getMessage();
                System.out.println("Messages processed : " + messageLoc + " Thread : " + Thread.currentThread()
                                                                                               .getName());
            } catch (Exception exp) {
                logger.error("[ConsumerThread][run]-> *Error* : " + exp.getMessage(),
                             exp);
            }

        }
    }
}
