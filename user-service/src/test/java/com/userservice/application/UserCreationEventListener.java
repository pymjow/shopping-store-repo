package com.userservice.application;

import com.userservice.config.UserTopicsConfigProperties;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(UserTopicsConfigProperties.class)
@DirtiesContext
public class UserCreationEventListener {



    @ClassRule
    private EmbeddedKafkaRule embeddedKafkaRule=
            new EmbeddedKafkaRule(1,true,"");



}
