package org.ivangrod.needlehack.infrastructure.post.persistence.rabbitmq;

import org.ivangrod.needlehack.infrastructure.shared.bus.event.rabbitmq.RabbitMqEventBus;
import org.ivangrod.needlehack.infrastructure.shared.bus.event.rabbitmq.RabbitMqPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqPostEventBusConfiguration {

  private final RabbitMqPublisher publisher;

  public RabbitMqPostEventBusConfiguration(RabbitMqPublisher publisher) {
    this.publisher = publisher;
  }

  @Bean
  public RabbitMqEventBus rabbitMqEventBus() {
    return new RabbitMqEventBus(publisher);
  }
}
