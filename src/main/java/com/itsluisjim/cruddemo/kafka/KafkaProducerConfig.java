package com.itsluisjim.cruddemo.kafka;

import com.itsluisjim.cruddemo.entity.Employee;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // set up a production factory. This acts as a guide for forming Kafka Producer instances
    @Bean
    public ProducerFactory<String, Employee> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        // This property specifies the Kafka brokers' addresses, which are a comma-separated list of host-port pairs.
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        /* KEY_SERIALIZER_CLASS_CONFIG and VALUE_SERIALIZER_CLASS_CONFIG:
         * These properties determine how the key and value of a message will be serialized before being sent to Kafka.
         * In this example, we use StringSerializer for both key and value serialization.
        */
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }


    // We employ a KafkaTemplate, which wraps around a Producer instance and offers simple methods to send messages to specific Kafka topics
    @Bean
    public KafkaTemplate<String, Employee> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
