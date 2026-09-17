package com.kartik.userservice.desrializer;

import com.kartik.userservice.entities.UserInfoDto;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDto>{

    @Override
    public void configure(Map<String ,?> configs, boolean isKey) {
        // No configuration needed for this deserializer
    }
    @Override
    public UserInfoDto deserialize(String arg0, byte[] arg1) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDto user=null;
        try{
            user=objectMapper.readValue(arg1,UserInfoDto.class);

        } catch (Exception e) {
            System.out.println("Error deserializing UserInfoDto: " + e.getMessage());
        }

        return user;
    }
    @Override
    public void close(){}
}
