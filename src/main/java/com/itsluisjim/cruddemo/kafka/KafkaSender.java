package com.itsluisjim.cruddemo.kafka;

import com.itsluisjim.cruddemo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    public void sendMessage(Employee employee, String topicName){
        log.info("Sending: {}", employee);
        log.info("-------------------------------");

        kafkaTemplate.send(topicName, employee);
    }
}
