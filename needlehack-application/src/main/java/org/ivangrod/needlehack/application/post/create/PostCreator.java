package org.ivangrod.needlehack.application.post.create;

import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.domain.post.PostId;
import org.ivangrod.needlehack.domain.post.PostRepository;
import org.ivangrod.needlehack.domain.shared.event.EventBus;

public class PostCreator {

  private final PostRepository postRepository;
  private final EventBus eventBus;

  public PostCreator(PostRepository postRepository, EventBus eventBus) {
    this.postRepository = postRepository;
    this.eventBus = eventBus;
  }

  public void execute(CreatingPostParams params) {
    Post postFromFeed = Post
        .create(PostId.buildFromUri(params.getUri().value()), params.getTitle(), params.getUri(),
            params.getAuthor(),
            params.getFeed(), params.getContent(), params.getPublicationDate(),
            params.getTopics());
    postRepository.save(postFromFeed);
    eventBus.publish(postFromFeed.pullDomainEvents());
  }
}
