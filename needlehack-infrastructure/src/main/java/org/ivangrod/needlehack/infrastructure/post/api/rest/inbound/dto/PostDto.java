package org.ivangrod.needlehack.infrastructure.post.api.rest.inbound.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class PostDto {

  private String title;

  private String uri;

  private String author;

  private String feedUri;

  private String feedSource;

  private String content;

  private LocalDateTime publicationDate;

  private Set<String> topics;

  public PostDto() {
  }

  public PostDto(String title, String uri, String author, String feedUri, String feedSource,
      String content, LocalDateTime publicationDate, Set<String> topics) {
    this.title = title;
    this.uri = uri;
    this.author = author;
    this.feedUri = feedUri;
    this.feedSource = feedSource;
    this.content = content;
    this.publicationDate = publicationDate;
    this.topics = topics;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getFeedUri() {
    return feedUri;
  }

  public void setFeedUri(String feedUri) {
    this.feedUri = feedUri;
  }

  public String getFeedSource() {
    return feedSource;
  }

  public void setFeedSource(String feedSource) {
    this.feedSource = feedSource;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDateTime publicationDate) {
    this.publicationDate = publicationDate;
  }

  public Set<String> getTopics() {
    return topics;
  }

  public void setTopics(Set<String> topics) {
    this.topics = topics;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostDto postDto = (PostDto) o;
    return Objects.equals(title, postDto.title) &&
        Objects.equals(uri, postDto.uri) &&
        Objects.equals(author, postDto.author) &&
        Objects.equals(feedUri, postDto.feedUri) &&
        Objects.equals(feedSource, postDto.feedSource) &&
        Objects.equals(content, postDto.content) &&
        Objects.equals(publicationDate, postDto.publicationDate) &&
        Objects.equals(topics, postDto.topics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, uri, author, feedUri, feedSource, content, publicationDate, topics);
  }

  @Override
  public String toString() {
    return "PostDto{" +
        "title='" + title + '\'' +
        ", uri='" + uri + '\'' +
        ", author='" + author + '\'' +
        ", feedUri='" + feedUri + '\'' +
        ", feedSource='" + feedSource + '\'' +
        ", content='" + content + '\'' +
        ", publicationDate=" + publicationDate +
        ", topics=" + topics +
        '}';
  }
}
