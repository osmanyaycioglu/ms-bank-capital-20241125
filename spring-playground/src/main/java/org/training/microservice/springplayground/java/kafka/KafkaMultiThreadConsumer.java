package org.training.microservice.springplayground.java.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class KafkaMultiThreadConsumer extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMultiThreadConsumer.class);
    private ConsumerQueue consumerQueue;
    private Properties propertiesLoc = new Properties();
    public KafkaMultiThreadConsumer(final ConsumerQueue consumerQueueParam,String clientId) {
        consumerQueue = consumerQueueParam;
        propertiesLoc.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                          "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        propertiesLoc.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                          IntegerDeserializer.class.getName());
        propertiesLoc.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                          StringDeserializer.class.getName());
        propertiesLoc.put(ConsumerConfig.GROUP_ID_CONFIG,
                          "cons-group-1");
        propertiesLoc.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                          "earliest");
        propertiesLoc.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                          "false");
        propertiesLoc.put(ConsumerConfig.CLIENT_ID_CONFIG,
                          clientId);
        propertiesLoc.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG,
                          clientId);

    }

    @Override
    public void run() {
        while (true) {
            try {
                try(KafkaConsumer<Integer, String> kafkaConsumerLoc = new KafkaConsumer<>(propertiesLoc))  {
                    kafkaConsumerLoc.subscribe(Arrays.asList("test-topic-1"));
                    while (true) {
                        ConsumerRecords<Integer, String> pollLoc = kafkaConsumerLoc.poll(Duration.ofMillis(1_000));
                        for (ConsumerRecord<Integer, String> cr : pollLoc) {
                            System.out.println(Thread.currentThread().getName() + " Received  : " + cr);
                            consumerQueue.addMessage(cr.value());
                        }
                        kafkaConsumerLoc.commitSync();
                    }
                }
            } catch (Exception exp) {
                logger.error("[KafkaMultiThreadConsumer][run]-> *Error* : " + exp.getMessage(),
                             exp);
            }

        }
    }
}
