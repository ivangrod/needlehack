package com.ivangrod.needlehack.pill.adapter.event.memory

import com.ivangrod.needlehack.pill.domain.event.DomainEvent
import com.ivangrod.needlehack.pill.domain.event.DomainEventPublisher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class InMemoryEventPublisher(private val delegatedPublisher: ApplicationEventPublisher) : DomainEventPublisher {

    override fun publish(event: DomainEvent) {
        delegatedPublisher.publishEvent(event)
            .also { log.info("Event published [{}]", event) }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(InMemoryEventPublisher::class.java)
    }
}