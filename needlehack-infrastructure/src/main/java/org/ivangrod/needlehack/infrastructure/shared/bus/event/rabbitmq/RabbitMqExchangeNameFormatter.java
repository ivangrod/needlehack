package org.ivangrod.needlehack.infrastructure.shared.bus.event.rabbitmq;

public final class RabbitMqExchangeNameFormatter {

  public static String retry(String exchangeName) {
    return String.format("retry-%s", exchangeName);
  }

  public static String deadLetter(String exchangeName) {
    return String.format("dead_letter-%s", exchangeName);
  }
}
