package org.ivangrod.needlehack.domain.post.event;

import static org.ivangrod.needlehack.domain.shared.format.DateFormatter.dateToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.ivangrod.needlehack.domain.shared.event.DomainEvent;

public class PostCollected extends DomainEvent {

    private final String title;

    private final String uri;

    private final String source;

    private final LocalDateTime publicatedAt;

    public PostCollected() {
        super(null);
        this.title = null;
        this.uri = null;
        this.source = null;
        this.publicatedAt = null;
    }

    public PostCollected(String aggregateId, String title, String uri, String source,
                         LocalDateTime publicatedAt) {
        super(aggregateId);
        this.title = title;
        this.uri = uri;
        this.source = source;
        this.publicatedAt = publicatedAt;
    }

    public PostCollected(String aggregateId, String eventId, LocalDateTime happenedAt, String title,
                         String uri, String source, LocalDateTime publicatedAt) {
        super(aggregateId, eventId, happenedAt);
        this.title = title;
        this.uri = uri;
        this.source = source;
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
            put("publicatedAt", dateToString(publicatedAt));
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body,
                                      String eventId, LocalDateTime happenedAt) {
        return new PostCollected(
                aggregateId,
                eventId,
                happenedAt,
                (String) body.get("title"),
                (String) body.get("uri"),
                (String) body.get("source"),
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
