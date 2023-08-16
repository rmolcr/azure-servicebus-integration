package com.myproject.demo.config

import com.myproject.demo.domain.Dummy
import com.myproject.demo.lib.EventSupplier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.Many

@Configuration
class DummyProducerConfig {

    @Bean
    fun dummySupplierMany(): Many<Message<Dummy>> = Sinks.many().unicast().onBackpressureBuffer()

    @Bean
    fun dummySupplier(many: Many<Message<Dummy>>) = EventSupplier(many)

}