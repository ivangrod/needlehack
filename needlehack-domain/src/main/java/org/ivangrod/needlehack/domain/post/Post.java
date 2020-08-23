package org.ivangrod.needlehack.domain.post;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.ivangrod.needlehack.domain.post.event.PostCollected;
import org.ivangrod.needlehack.domain.post.event.PostCreated;
import org.ivangrod.needlehack.domain.shared.AggregateRoot;
import org.ivangrod.needlehack.domain.shared.StringValueObject;

public class Post extends AggregateRoot {

    private final PostId id;

    private final PostTitle title;

    private final PostUri uri;

    private final Author creator;

    private final Feed origin;

    private final PostContent content;

    private final PostDate dates;

    private final Set<Topic> topics;

    private Post(PostId id, PostTitle title, PostUri uri, Author creator, Feed origin,
                 PostContent content, PostDate dates, Set<Topic> topics) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.creator = creator;
        this.origin = origin;
        this.content = content;
        this.dates = dates;
        this.topics = topics;
    }

    public static Post create(PostId id, PostTitle title, PostUri uri, Author creator,
                              Feed origin, PostContent content, PostDate dates, Set<Topic> topics) {
        Post post = new Post(id, title, uri, creator, origin, content, dates, topics);
        post.record(
                new PostCreated(id.getIdFromUri(), title.value(), uri.value(), dates.getPublicationAt()));
        return post;
    }

    public static Post collect(PostId id, PostTitle title, PostUri uri, Author creator,
                               Feed origin, PostDate dates) {

        //TODO Consume content and topics from collecting process
        Post post = new Post(id, title, uri, creator, origin, null, dates, Collections.emptySet());
        post.record(
                new PostCollected(id.getIdFromUri(), title.value(), uri.value(), origin.getSource(),
                        dates.getPublicationAt()));
        return post;
    }

    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("id", id.getIdFromUri());
            put("title", title.value());
            put("uri", uri.value());
            put("creator", creator.value());
            put("origin", origin.getSource());
            put("feedUri", origin.getUri());
            put("content", content.value());
            put("publication_date", dates.publicationDateToStringFormat());
            put("topics",
                    topics.stream().map(StringValueObject::value).collect(Collectors.joining(",")));
        }};
    }

    public static Post fromPrimitives(Map<String, Object> plainData) {

        return new Post(PostId.buildFromIdentifier((String) plainData.get("uri")),
                new PostTitle((String) plainData.get("title")),
                new PostUri((String) plainData.get("uri")),
                new Author((String) plainData.get("creator")),
                new Feed((String) plainData.get("origin"), (String) plainData.get("feedUri")),
                new PostContent((String) plainData.get("content")),
                PostDate.buildStringToPublicationDate((String) plainData.get("publication_date")),
                new HashSet(Arrays.asList(((String) plainData.get("topics")).split(","))));
    }

    public PostId getId() {
        return id;
    }

    public PostTitle getTitle() {
        return title;
    }

    public PostUri getUri() {
        return uri;
    }

    public Author getCreator() {
        return creator;
    }

    public Feed getOrigin() {
        return origin;
    }

    public PostContent getContent() {
        return content;
    }

    public PostDate getDates() {
        return dates;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(uri, post.uri) &&
                Objects.equals(creator, post.creator) &&
                Objects.equals(origin, post.origin) &&
                Objects.equals(content, post.content) &&
                Objects.equals(dates, post.dates) &&
                Objects.equals(topics, post.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, uri, creator, origin, content, dates, topics);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title=" + title +
                ", uri=" + uri +
                ", creator=" + creator +
                ", origin=" + origin +
                ", content=" + content +
                ", dates=" + dates +
                ", topics=" + topics +
                '}';
    }
}
