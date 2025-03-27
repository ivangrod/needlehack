package com.ivangrod.needlehack.pill.domain.event

fun interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}