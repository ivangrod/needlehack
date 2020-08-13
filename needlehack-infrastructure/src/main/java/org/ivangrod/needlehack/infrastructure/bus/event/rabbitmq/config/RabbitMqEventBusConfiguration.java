package org.ivangrod.needlehack.infrastructure.bus.event.rabbitmq.config;

import org.ivangrod.needlehack.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;
import org.ivangrod.needlehack.infrastructure.bus.event.rabbitmq.RabbitMqPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqEventBusConfiguration {

    private final RabbitMqPublisher publisher;

    public RabbitMqEventBusConfiguration(RabbitMqPublisher publisher) {
        this.publisher = publisher;
    }

    @Bean
    public RabbitMqEventBus rabbitMqEventBus() {
        return new RabbitMqEventBus(publisher);
    }
}
