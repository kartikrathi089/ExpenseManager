package org.example.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.Model.UserInfoDto;
import org.example.eventProducer.UserInfoEvent;

import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoEvent> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }



    @Override
    public void close() {
        Serializer.super.close();
    }

    @Override
    public byte[] serialize(String s, UserInfoEvent data) {
        byte[] serializedData = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serializedData = objectMapper.writeValueAsString(data).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }



        return serializedData;
    }
}
