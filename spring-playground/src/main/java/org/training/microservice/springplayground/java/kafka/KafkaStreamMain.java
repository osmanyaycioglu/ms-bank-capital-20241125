package org.training.microservice.springplayground.java.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class KafkaStreamMain {

    public static void main(String[] args) {
        Properties propertiesLoc = new Properties();
        propertiesLoc.put(StreamsConfig.APPLICATION_ID_CONFIG,
                          "stream-tryout");
        propertiesLoc.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
                          "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        propertiesLoc.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                          Serdes.Integer()
                                .getClass());
        propertiesLoc.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                          Serdes.String()
                                .getClass());
        propertiesLoc.put(StreamsConfig.CLIENT_ID_CONFIG,
                          "client1");
        propertiesLoc.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                          "earliest");

        StreamsBuilder           streamsBuilderLoc = new StreamsBuilder();
        KStream<Integer, String> streamLoc         = streamsBuilderLoc.stream("test-topic-1");

        streamLoc.foreach(((key, value) -> System.out.println("Received key : " + key + " value : " + value)));

        streamLoc.peek((k, v) -> System.out.println("peek key : " + k + " value : " + v))
                 .filter((k, v) -> v.length() > 15)
                 .mapValues(v -> v + " stream")
                 .to("another-topic",
                     Produced.with(Serdes.Integer(),
                                   Serdes.String()));

        Topology topologyLoc = streamsBuilderLoc.build();
        KafkaStreams streamsLoc = new KafkaStreams(topologyLoc,
                                                   propertiesLoc);
        streamsLoc.start();

        Runtime.getRuntime()
               .addShutdownHook(new Thread(() -> {
                   System.out.println("Shutting down");
                   streamsLoc.close();
               }));
    }
}
