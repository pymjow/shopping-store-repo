package com.userservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.kafka")
public class UserTopicsConfigProperties {

    private String userCreationTopic;

    public String getUserCreationTopic() {
        return userCreationTopic;
    }

    public void setUserCreationTopic(String userCreationTopic) {
        this.userCreationTopic = userCreationTopic;
    }



}
