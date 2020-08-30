package org.ivangrod.needlehack.infrastructure.post.service.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ivangrod.needlehack.domain.post.Author;
import org.ivangrod.needlehack.domain.post.Feed;
import org.ivangrod.needlehack.domain.post.FeedExtractor;
import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.domain.post.PostContent;
import org.ivangrod.needlehack.domain.post.PostDate;
import org.ivangrod.needlehack.domain.post.PostId;
import org.ivangrod.needlehack.domain.post.PostTitle;
import org.ivangrod.needlehack.domain.post.PostUri;
import org.ivangrod.needlehack.domain.post.Topic;
import org.ivangrod.needlehack.infrastructure.shared.format.JsoupProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RssFeedExtractor implements FeedExtractor {

  private final static Logger log = LoggerFactory.getLogger(RssFeedExtractor.class);

  @Override
  public List<Post> extract(final Feed feed) {

    log.debug("Extracting RSS from feed [{}]", feed.getSource());

    List<Post> postsCollected = new ArrayList<>();

    try {

      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feedLoaded = input.build(new XmlReader(new URL(feed.getUri())));

      postsCollected.addAll(feedLoaded.getEntries()
          .stream()
          .map(entry -> Post
              .collect(PostId.buildFromUri(entry.getLink()), new PostTitle(entry.getTitle()),
                  new PostUri(entry.getLink()), new Author(entry.getAuthor()), feed,
                  extractContent(entry),
                  new PostDate(entry.getPublishedDate()), extractTopics(entry)))
          .collect(Collectors.toList()));

    } catch (Exception exception) {
      log.error("An error has been produced when the feed from source [{}] was loaded",
          feed.getSource());
    }

    return postsCollected;
  }

  private PostContent extractContent(SyndEntry entryFromFeed) {

    PostContent content = PostContent.buildWithContentPlain(StringUtils.EMPTY);

    if (!CollectionUtils.isEmpty(entryFromFeed.getContents())) {
      StringBuilder strBuilder = new StringBuilder();
      entryFromFeed.getContents()
          .forEach(contentFromEntry -> strBuilder.append(contentFromEntry.getValue()));
      content = PostContent.buildWithContentProcessed(strBuilder.toString(), new JsoupProcessor());
    }
    return content;
  }

  private Set<Topic> extractTopics(SyndEntry entryFromFeed) {

    Set<Topic> topics = new HashSet<>();
    if (!CollectionUtils.isEmpty(entryFromFeed.getCategories())) {
      topics = entryFromFeed.getCategories()
          .stream()
          .map(entryCategory -> new Topic(entryCategory.getName()))
          .collect(Collectors.toSet());
    }
    return topics;
  }
}
