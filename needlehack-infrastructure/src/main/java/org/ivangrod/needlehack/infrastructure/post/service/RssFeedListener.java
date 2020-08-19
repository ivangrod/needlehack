package org.ivangrod.needlehack.infrastructure.post.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.ivangrod.needlehack.domain.post.Feed;
import org.ivangrod.needlehack.domain.post.FeedListener;
import org.ivangrod.needlehack.domain.post.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RssFeedListener implements FeedListener {

  private final static Logger log = LoggerFactory.getLogger(RssFeedListener.class);

  @Override
  public List<Post> extract(final Feed feed) {

    log.info("Extracting RSS from feed [{}]", feed.getSource());

    List<Post> postsCollected = new ArrayList<>();

    try {

      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feedLoaded = input.build(new XmlReader(new URL(feed.getUri())));

      postsCollected.addAll(feedLoaded.getEntries()
          .stream()
          .map(entry -> Post.collect(feed, entry))
          .collect(Collectors.toList()));

    } catch (Exception exception) {
      log.error("An error has been produced when the feed from source [{}] was loaded",
          feed.getSource());
    }

    return postsCollected;
  }
}
