package org.ivangrod.needlehack.application.post.collect;

import java.util.List;
import org.ivangrod.needlehack.domain.post.Feed;
import org.ivangrod.needlehack.domain.post.FeedListener;
import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.domain.shared.event.EventBus;

public class PostCollector {

  private final FeedListener feedListener;
  private final EventBus eventBus;

  public PostCollector(FeedListener feedListener, EventBus eventBus) {
    this.feedListener = feedListener;
    this.eventBus = eventBus;
  }

  public List<Post> execute(CollectingPostParams params) {

    Feed feed = params.createFeed();

    List<Post> postsCollected = feedListener.extract(feed);
    postsCollected.forEach(post -> eventBus.publish(post.pullDomainEvents()));

    return postsCollected;
  }
}
