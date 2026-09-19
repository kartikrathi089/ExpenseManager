package com.kartik.userservice.consumer;

import com.kartik.userservice.entities.UserInfoDto;
import com.kartik.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.kartik.userservice.service.UserService;
import tools.jackson.databind.ObjectMapper;

@Component
public class AuthServiceConsumer {

    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    @KafkaListener(topics ="${spring.kafka.topic-json.name}",groupId="${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData){
        try{
            // Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc
            userService.createOrUpdateUser(eventData);

        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }

    }
}
