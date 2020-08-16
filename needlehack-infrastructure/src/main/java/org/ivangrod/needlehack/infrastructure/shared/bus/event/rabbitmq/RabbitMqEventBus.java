package org.ivangrod.needlehack.infrastructure.shared.bus.event.rabbitmq;

import java.util.List;
import org.ivangrod.needlehack.domain.shared.event.DomainEvent;
import org.ivangrod.needlehack.domain.shared.event.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;

public class RabbitMqEventBus implements EventBus {

  private static final Logger log = LoggerFactory.getLogger(RabbitMqEventBus.class);

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
      log.error(error.getMessage(), error);
    }
  }
}
