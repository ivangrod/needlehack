package org.ivangrod.needlehack.application.post.create;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.ivangrod.needlehack.domain.post.Author;
import org.ivangrod.needlehack.domain.post.Feed;
import org.ivangrod.needlehack.domain.post.PostContent;
import org.ivangrod.needlehack.domain.post.PostDate;
import org.ivangrod.needlehack.domain.post.PostTitle;
import org.ivangrod.needlehack.domain.post.PostUri;
import org.ivangrod.needlehack.domain.post.Topic;

public class CreatingPostParams {

  private final String title;

  private final String uri;

  private final String author;

  private final String feedUri;

  private final String feedSource;

  private final String content;

  private final LocalDateTime publicationDate;

  private final Set<String> topics;

  public CreatingPostParams(String title, String uri, String author, String feedUri,
      String feedSource, String content, LocalDateTime publicationDate,
      Set<String> topics) {
    this.title = title;
    this.uri = uri;
    this.author = author;
    this.feedUri = feedUri;
    this.feedSource = feedSource;
    this.content = content;
    this.publicationDate = publicationDate;
    this.topics = topics;
  }

  public PostTitle getTitle() {
    return new PostTitle(title);
  }

  public PostUri getUri() {
    return new PostUri(uri);
  }

  public Author getAuthor() {
    return new Author(author);
  }

  public Feed getFeed() {
    return new Feed(feedUri, feedSource);
  }

  public PostContent getContent() {
    return new PostContent(content);
  }

  public PostDate getPublicationDate() {
    return new PostDate(publicationDate);
  }

  public Set<Topic> getTopics() {
    return topics.stream().map(Topic::new).collect(Collectors.toSet());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatingPostParams that = (CreatingPostParams) o;
    return Objects.equals(title, that.title) &&
        Objects.equals(uri, that.uri) &&
        Objects.equals(author, that.author) &&
        Objects.equals(feedUri, that.feedUri) &&
        Objects.equals(feedSource, that.feedSource) &&
        Objects.equals(content, that.content) &&
        Objects.equals(publicationDate, that.publicationDate) &&
        Objects.equals(topics, that.topics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, uri, author, feedUri, feedSource, content, publicationDate, topics);
  }

  @Override
  public String toString() {
    return "CreatingPostParams{" +
        "title='" + title + '\'' +
        ", uri='" + uri + '\'' +
        ", author='" + author + '\'' +
        ", feedUri='" + feedUri + '\'' +
        ", feedSource='" + feedSource + '\'' +
        ", content='" + content + '\'' +
        ", publicationDate='" + publicationDate + '\'' +
        ", topic=" + topics +
        '}';
  }
}
