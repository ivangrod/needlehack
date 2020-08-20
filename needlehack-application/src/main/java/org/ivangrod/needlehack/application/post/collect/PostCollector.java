package org.ivangrod.needlehack.application.post.collect;

import java.util.List;
import org.ivangrod.needlehack.domain.post.Feed;
import org.ivangrod.needlehack.domain.post.FeedExtractor;
import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.domain.shared.event.EventBus;

public class PostCollector {

  private final FeedExtractor feedExtractor;
  private final EventBus eventBus;

  public PostCollector(FeedExtractor feedExtractor, EventBus eventBus) {
    this.feedExtractor = feedExtractor;
    this.eventBus = eventBus;
  }

  public List<Post> execute(CollectingPostParams params) {

    Feed feed = params.createFeed();

    List<Post> postsCollected = feedExtractor.extract(feed);
    postsCollected.forEach(post -> eventBus.publish(post.pullDomainEvents()));

    return postsCollected;
  }
}
