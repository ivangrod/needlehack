package org.ivangrod.needlehack.domain.shared.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public abstract class DomainEvent {

    private String aggregateId;
    private String eventId;
    private LocalDateTime happenedAt;

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = UUID.randomUUID().toString();
        this.happenedAt = LocalDateTime.now();
    }

    public DomainEvent(String aggregateId, String eventId, LocalDateTime happenedAt) {
        this.aggregateId = aggregateId;
        this.eventId = eventId;
        this.happenedAt = happenedAt;
    }

    protected DomainEvent() {
    }

    public abstract String eventName();

    public abstract HashMap<String, Serializable> toPrimitives();

    public abstract DomainEvent fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            LocalDateTime happenedAt
    );

    public String aggregateId() {
        return aggregateId;
    }

    public String eventId() {
        return eventId;
    }

    public LocalDateTime happenedAt() {
        return happenedAt;
    }
}