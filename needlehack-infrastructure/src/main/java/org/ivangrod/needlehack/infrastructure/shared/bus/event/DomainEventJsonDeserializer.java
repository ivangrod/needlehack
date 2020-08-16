package org.ivangrod.needlehack.infrastructure.shared.bus.event;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.ivangrod.needlehack.domain.shared.event.DomainEvent;
import org.ivangrod.needlehack.infrastructure.shared.format.JsonProcessor;
import org.springframework.stereotype.Service;

@Service
public final class DomainEventJsonDeserializer {

  private final DomainEventsInformation information;

  public DomainEventJsonDeserializer(DomainEventsInformation information) {
    this.information = information;
  }

  public DomainEvent deserialize(String body)
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
    HashMap<String, Serializable> eventData = JsonProcessor.decode(body);
    HashMap<String, Serializable> data = (HashMap<String, Serializable>) eventData.get("data");
    HashMap<String, Serializable> attributes = (HashMap<String, Serializable>) data
        .get("attributes");
    Class<? extends DomainEvent> domainEventClass = information.forName((String) data.get("type"));

    DomainEvent nullInstance = domainEventClass.getConstructor().newInstance();

    Method fromPrimitivesMethod = domainEventClass.getMethod(
        "fromPrimitives",
        String.class,
        HashMap.class,
        String.class,
        String.class
    );

    Object domainEvent = fromPrimitivesMethod.invoke(
        nullInstance,
        (String) attributes.get("id"),
        attributes,
        (String) data.get("id"),
        (String) data.get("happened_at")
    );

    return (DomainEvent) domainEvent;
  }
}
