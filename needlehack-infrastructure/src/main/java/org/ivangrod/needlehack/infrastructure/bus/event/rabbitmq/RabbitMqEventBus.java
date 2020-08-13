package org.ivangrod.needlehack.infrastructure.bus.event.rabbitmq;

import java.util.List;
import org.ivangrod.needlehack.domain.shared.event.DomainEvent;
import org.ivangrod.needlehack.domain.shared.event.EventBus;
import org.springframework.amqp.AmqpException;

public class RabbitMqEventBus implements EventBus {

    private final RabbitMqPublisher publisher;
    private final String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher = publisher;
        this.exchangeName = "domain_events";
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
        try {
            this.publisher.publish(domainEvent, exchangeName);
        } catch (AmqpException error) {
            // TODO: Implement failover operation
        }
    }
}
