package org.ivangrod.needlehack.application.post.search;

import java.util.Objects;
import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.domain.shared.format.DateFormatter;

public class PostFound {

    private String id;

    private String title;

    private String uri;

    private String creator;

    private String origin;

    private String publishedAt;

    public PostFound() {
    }

    private PostFound(String id, String title, String uri, String creator, String origin, String publishedAt) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.creator = creator;
        this.origin = origin;
        this.publishedAt = publishedAt;
    }

    public static PostFound fromPost(Post post) {
        return new PostFound(
                post.getId().value(),
                post.getTitle().value(),
                post.getUri().value(),
                post.getCreator().value(),
                post.getOrigin().getSource(),
                DateFormatter.dateToString(post.getDates().getPublicationAt()));
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }

    public String getCreator() {
        return creator;
    }

    public String getOrigin() {
        return origin;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostFound postFound = (PostFound) o;
        return Objects.equals(id, postFound.id) &&
                Objects.equals(title, postFound.title) &&
                Objects.equals(uri, postFound.uri) &&
                Objects.equals(creator, postFound.creator) &&
                Objects.equals(origin, postFound.origin) &&
                Objects.equals(publishedAt, postFound.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, uri, creator, origin, publishedAt);
    }

    @Override
    public String toString() {
        return "PostFound{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", uri='" + uri + '\'' +
                ", creator='" + creator + '\'' +
                ", origin='" + origin + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}
