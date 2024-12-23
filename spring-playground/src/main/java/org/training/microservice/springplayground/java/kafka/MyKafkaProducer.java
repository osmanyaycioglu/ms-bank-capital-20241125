package org.training.microservice.springplayground.java.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyKafkaProducer {

    public static void main(String[] args) {
        Properties propertiesLoc = new Properties();
        propertiesLoc.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                          "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        propertiesLoc.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                          IntegerSerializer.class.getName());
        propertiesLoc.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                          StringSerializer.class.getName());
        try (KafkaProducer<Integer, String> kafkaProducerLoc = new KafkaProducer<>(propertiesLoc)) {
            for (int i = 0; i < 100; i++) {
                kafkaProducerLoc.send(new ProducerRecord<>("test-topic-1",
                                                           i,
                                                           "deneme mesajı " + i));
            }
        }

    }

}
