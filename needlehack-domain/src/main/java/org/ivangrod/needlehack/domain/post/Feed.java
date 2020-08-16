package org.ivangrod.needlehack.domain.post;

import java.util.Objects;

public class Feed {

  private String uri;

  private String source;

  public Feed(String uri, String source) {
    this.uri = uri;
    this.source = source;
  }

  public String getUri() {
    return uri;
  }

  public String getSource() {
    return source;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Feed feed = (Feed) o;
    return Objects.equals(uri, feed.uri) &&
        Objects.equals(source, feed.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri, source);
  }

  @Override
  public String toString() {
    return "Feed{" +
        "uri='" + uri + '\'' +
        ", source='" + source + '\'' +
        '}';
  }
}
