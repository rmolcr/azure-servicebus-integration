package com.myproject.demo.rest

import com.myproject.demo.events.DummyEventProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestParam
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress


@RestController
class DemoController(
        @Autowired private val dummyEventProducer: DummyEventProducer,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/send")
    fun sendMessage(
        @RequestParam msg: String = "dummy message"
    ): ResponseEntity<String> {
        dummyEventProducer.sendDummyEvent(msg)
        return ResponseEntity.ok("Sent!")
    }

    @GetMapping("/")
    fun health(): ResponseEntity<String> {
        return ResponseEntity("Status ok. Running on: " + InetAddress.getLocalHost().hostName, HttpStatus.OK)
    }
}