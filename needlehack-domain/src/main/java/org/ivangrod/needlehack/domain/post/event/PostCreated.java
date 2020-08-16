package org.ivangrod.needlehack.domain.post.event;

import static org.ivangrod.needlehack.domain.shared.format.DateFormatter.dateToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.ivangrod.needlehack.domain.shared.event.DomainEvent;

public class PostCreated extends DomainEvent {

  private final String title;

  private final String uri;

  private final LocalDateTime publicatedAt;

  public PostCreated() {
    super(null);
    this.title = null;
    this.uri = null;
    this.publicatedAt = null;
  }

  public PostCreated(String aggregateId, String title, String uri, LocalDateTime publicatedAt) {
    super(aggregateId);
    this.title = title;
    this.uri = uri;
    this.publicatedAt = publicatedAt;
  }

  public PostCreated(String aggregateId, String eventId, LocalDateTime happenedAt, String title,
      String uri, LocalDateTime publicatedAt) {
    super(aggregateId, eventId, happenedAt);
    this.title = title;
    this.uri = uri;
    this.publicatedAt = publicatedAt;
  }

  @Override
  public String eventName() {
    return "post.created";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<>() {{
      put("title", title);
      put("uri", uri);
      put("publicatedAt", dateToString(publicatedAt));
    }};
  }

  @Override
  public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body,
      String eventId, LocalDateTime happenedAt) {
    return new PostCreated(
        aggregateId,
        eventId,
        happenedAt,
        (String) body.get("title"),
        (String) body.get("uri"),
        (LocalDateTime) body.get("publicatedAt")
    );
  }
}
