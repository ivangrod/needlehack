package org.ivangrod.needlehack.domain.post.event;

import static org.ivangrod.needlehack.domain.shared.format.DateFormatter.dateToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.ivangrod.needlehack.domain.shared.event.DomainEvent;

public class PostCollected extends DomainEvent {

  private final String title;

  private final String uri;

  private final String source;

  private final String author;

  private final String feedUri;

  private final String content;

  private final Set<String> topics;

  private final LocalDateTime publicatedAt;

  public PostCollected() {
    super(null);
    this.title = null;
    this.uri = null;
    this.source = null;
    this.publicatedAt = null;
    this.author = null;
    this.feedUri = null;
    this.content = null;
    this.topics = Collections.emptySet();
  }

  public PostCollected(String aggregateId, String title, String uri, String source,
      LocalDateTime publicatedAt) {
    super(aggregateId);
    this.title = title;
    this.uri = uri;
    this.source = source;
    this.publicatedAt = publicatedAt;
    this.author = null;
    this.feedUri = null;
    this.content = null;
    this.topics = Collections.emptySet();
  }

  public PostCollected(String aggregateId, String eventId, LocalDateTime happenedAt, String title,
      String uri, String source, LocalDateTime publicatedAt) {
    super(aggregateId, eventId, happenedAt);
    this.title = title;
    this.uri = uri;
    this.source = source;
    this.publicatedAt = publicatedAt;
    this.author = null;
    this.feedUri = null;
    this.content = null;
    this.topics = Collections.emptySet();
  }


  public PostCollected(String aggregateId, String title,
      String uri, String source, String author, String feedUri, String content, Set<String> topics,
      LocalDateTime publicatedAt) {
    super(aggregateId);
    this.title = title;
    this.uri = uri;
    this.source = source;
    this.author = author;
    this.feedUri = feedUri;
    this.content = content;
    this.topics = topics;
    this.publicatedAt = publicatedAt;
  }

  public String getTitle() {
    return title;
  }

  public String getUri() {
    return uri;
  }

  public String getSource() {
    return source;
  }

  public LocalDateTime getPublicatedAt() {
    return publicatedAt;
  }

  public String getAuthor() {
    return author;
  }

  public String getFeedUri() {
    return feedUri;
  }

  public String getContent() {
    return content;
  }

  public Set<String> getTopics() {
    return topics;
  }

  @Override
  public String eventName() {
    return "post.collected";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<>() {{
      put("title", title);
      put("uri", uri);
      put("source", source);
      put("author", author);
      put("feedUri", feedUri);
      put("content", content);
      put("topics", topics.stream().collect(Collectors.joining(",")));
      put("publicatedAt", dateToString(publicatedAt));
    }};
  }

  @Override
  public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body,
      String eventId, LocalDateTime happenedAt) {
    return new PostCollected(
        aggregateId,
        (String) body.get("title"),
        (String) body.get("uri"),
        (String) body.get("source"),
        (String) body.get("author"),
        (String) body.get("feedUri"),
        (String) body.get("content"),
        new HashSet(Arrays.asList(((String) body.get("topics")).split(","))),
        (LocalDateTime) body.get("publicatedAt")
    );
  }

  @Override
  public String toString() {
    return "PostCollected {" +
        "title='" + title + '\'' +
        ", uri='" + uri + '\'' +
        ", source='" + source + '\'' +
        ", publicatedAt=" + publicatedAt +
        '}';
  }
}
