package org.ivangrod.needlehack.infrastructure.shared.bus.event.spring;

import java.util.List;
import org.ivangrod.needlehack.domain.shared.event.DomainEvent;
import org.ivangrod.needlehack.domain.shared.event.EventBus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class SpringApplicationEventBus implements EventBus {

  private final ApplicationEventPublisher publisher;

  public SpringApplicationEventBus(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void publish(final List<DomainEvent> events) {
    events.forEach(this::publish);
  }

  private void publish(final DomainEvent event) {
    this.publisher.publishEvent(event);
  }
}
