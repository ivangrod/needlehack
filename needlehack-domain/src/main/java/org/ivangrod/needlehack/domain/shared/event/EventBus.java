package org.ivangrod.needlehack.domain.shared.event;

import java.util.List;

public interface EventBus {

  void publish(final List<DomainEvent> events);
}
