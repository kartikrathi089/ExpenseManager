package org.example.eventProducer;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.example.Model.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@Service
public class UserInfoProducer {

    private final KafkaTemplate<String, UserInfoDto> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    UserInfoProducer(KafkaTemplate<String, UserInfoDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventToKafka(UserInfoEvent eventData) {
        Message<UserInfoEvent> message= MessageBuilder.withPayload(eventData)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }
}
