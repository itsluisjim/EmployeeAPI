package com.itsluisjim.cruddemo.kafka;

import com.itsluisjim.cruddemo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component
@Slf4j
public class KafkaListenerExample {

    // alternative way using regex in ONE go
    //    @KafkaListener(topicPattern = "user-.*-topic", containerFactory = "employeeKafkaListenerContainerFactory")
    //    void listenerWithTopicPattern(Employee employee) {
    //        log.info("Received message through TopicPatternListener [{}]", employee);
    //    }


    // If we don’t specify the containerFactory attribute, it will take defaults to kafkaListenerContainerFactory which uses StringSerializer and StringDeserializer
    @KafkaListener(topics = "user-creation-topic", containerFactory = "employeeKafkaListenerContainerFactory")
    void listenerForUserCreationTopic(Employee employee, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timeStamp, @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message through listenerForUserCreationTopic [{}]", employee);
        log.info("Partition: {}", partition);
        log.info("Timestamp: {}", timeStamp);
        log.info("Offset: {}", offset);
    }

    @KafkaListener(topics = "user-deletion-topic", containerFactory = "employeeKafkaListenerContainerFactory")
    void listenerForUserDeletionTopic(Employee employee, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timeStamp, @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message through listenerForUserDeletionTopic [{}]", employee);
        log.info("Partition: {}", partition);
        log.info("Timestamp: {}", timeStamp);
        log.info("Offset: {}", offset);
    }
}
