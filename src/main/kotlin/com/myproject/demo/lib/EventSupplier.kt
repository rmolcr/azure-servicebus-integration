package com.myproject.demo.lib

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks.Many
import java.util.function.Supplier

// THIS CLASS IS MEANT TO BE GENERIC, TO SIMPLIFY CONFIG.
class EventSupplier<T : Any>(
    private val many: Many<Message<T>>
) : Supplier<Flux<Message<T>>> {

    override fun get(): Flux<Message<T>> = many.asFlux()
        .doOnNext { m: Message<T> -> logger.info("Sending event of type; {}", m.payload::class.simpleName) }
        .doOnError { t: Throwable? -> logger.error("Error sending message", t) }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(EventSupplier::class.java)
    }
}