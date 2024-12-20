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
        Properties propertiesLoc = new Properties();
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


        try (KafkaConsumer<Integer, String> kafkaConsumerLoc = new KafkaConsumer<>(propertiesLoc)) {
            kafkaConsumerLoc.subscribe(Arrays.asList("test-topic-1"));
            while (true) {
                ConsumerRecords<Integer, String> pollLoc = kafkaConsumerLoc.poll(Duration.ofMillis(1_000));
                for (ConsumerRecord<Integer, String> cr : pollLoc) {
                    System.out.println("Received  : " + cr);
                }
            }

        }

    }

}
