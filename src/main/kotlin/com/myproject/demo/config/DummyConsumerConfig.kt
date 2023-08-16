package com.myproject.demo.config

import com.myproject.demo.domain.Dummy
import com.myproject.demo.events.DummyEventConsumer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import java.util.function.Consumer

@Configuration
class DummyConsumerConfig {

    @Bean
    fun dummyConsumer(): Consumer<Message<Dummy>> = DummyEventConsumer()

}