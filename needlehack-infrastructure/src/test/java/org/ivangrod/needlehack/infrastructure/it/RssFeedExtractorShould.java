package org.ivangrod.needlehack.infrastructure.it;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.ivangrod.needlehack.domain.post.Feed;
import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.infrastructure.Application;
import org.ivangrod.needlehack.infrastructure.post.service.rss.RssFeedExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RssFeedExtractorShould {

  @Autowired
  private RssFeedExtractor rssFeedExtractor;

  @Test
  public void return_a_collection_of_posts_given_a_feed_with_an_rss_uri() {
    List<Post> posts = rssFeedExtractor
        .extract(new Feed("https://blog.arkency.com/atom.xml", "Arkency"));

    assertTrue(posts.size() > 0);
  }
}
