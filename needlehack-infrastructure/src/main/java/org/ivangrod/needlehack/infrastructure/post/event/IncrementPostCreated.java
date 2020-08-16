package org.ivangrod.needlehack.infrastructure.post.event;

import java.util.concurrent.atomic.AtomicInteger;
import org.ivangrod.needlehack.domain.post.event.PostCreated;
import org.ivangrod.needlehack.domain.shared.event.DomainEventSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@DomainEventSubscriber({PostCreated.class})
public class IncrementPostCreated {

  private static final Logger log = LoggerFactory.getLogger(IncrementPostCreated.class);

  private final AtomicInteger incrementer;

  public IncrementPostCreated() {
    this.incrementer = new AtomicInteger();
  }

  @EventListener
  public void on(PostCreated event) {
    incrementer.addAndGet(1);
    log.info("The total number of post created is: [{}]", incrementer.get());
  }
}