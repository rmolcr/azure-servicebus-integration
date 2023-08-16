package com.myproject.demo.events

import com.myproject.demo.domain.Dummy
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import java.util.function.Consumer

class DummyEventConsumer : Consumer<Message<Dummy>> {
    override fun accept(message: Message<Dummy>) {
        logger.info("New message received: '{}'", message.payload)
        logger.info("New message received (String): '{}'", message.payload.toString())
        logger.info("Payload type: {}", message.payload::class.simpleName)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DummyEventConsumer::class.java)
    }

}