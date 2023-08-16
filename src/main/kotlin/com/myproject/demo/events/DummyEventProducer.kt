package com.myproject.demo.events

import com.myproject.demo.domain.Dummy
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import reactor.core.publisher.Sinks
import java.time.LocalDateTime

@Component
class DummyEventProducer(
        @Autowired private val dummySupplierMany: Sinks.Many<Message<Dummy>>,
) {

    fun sendDummyEvent(messageContent: String): Dummy {
        val event = Dummy(
            text = messageContent,
            created = LocalDateTime.now().toString(),
            author = "it's me"
        )

        logger.info("Going to add message {} to Sinks.Many.Dummy", event)
        dummySupplierMany.emitNext(
                MessageBuilder.withPayload(event).build(),
                Sinks.EmitFailureHandler.FAIL_FAST
        )
        return event
    }
    companion object {
        private val logger = LoggerFactory.getLogger(DummyEventProducer::class.java)
    }
}