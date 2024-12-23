package org.training.microservice.springplayground.java.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MyKafkaConsumer {
    public static void main(String[] args) {

        ConsumerQueue consumerQueueLoc = new ConsumerQueue();
        for (int i = 0; i < 5; i++) {
            KafkaMultiConsumer consumerLoc = new KafkaMultiConsumer(consumerQueueLoc,
                                                                                "cc-" + i);
            consumerLoc.setName("consumer-" + i);
            consumerLoc.start();
        }


    }

    public static void main2(String[] args) {

        ConsumerQueue consumerQueueLoc = new ConsumerQueue();
        for (int i = 0; i < 3; i++) {
            KafkaMultiThreadConsumer consumerLoc = new KafkaMultiThreadConsumer(consumerQueueLoc,
                                                                                "cc-" + i);
            consumerLoc.setName("consumer-" + i);
            consumerLoc.start();
        }
        for (int i = 0; i < 10; i++) {
            ConsumerThread consumerThreadLoc = new ConsumerThread(consumerQueueLoc);
            consumerThreadLoc.setName("process-" + i);
            consumerThreadLoc.start();
        }


    }

}
