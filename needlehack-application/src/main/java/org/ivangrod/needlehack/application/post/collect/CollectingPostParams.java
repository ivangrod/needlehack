package org.ivangrod.needlehack.application.post.collect;

import org.ivangrod.needlehack.domain.post.Feed;

public class CollectingPostParams {

  private final String feedUri;

  private final String source;

  public CollectingPostParams(String feedUri, String source) {
    this.feedUri = feedUri;
    this.source = source;
  }

  @Override
  public String toString() {
    return "CollectingPostParams{" +
        "feedUri='" + feedUri + '\'' +
        ", source='" + source + '\'' +
        '}';
  }

  public Feed createFeed() {
    return new Feed(feedUri, source);
  }
}
