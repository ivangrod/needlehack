package org.ivangrod.needlehack.infrastructure.post.service.rss;

import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedInput;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.ivangrod.needlehack.application.post.collect.CollectingPostParams;
import org.ivangrod.needlehack.application.post.collect.PostCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.xml.sax.InputSource;

@Component
public class RssReader {

  private static final Logger logger = LoggerFactory.getLogger(RssReader.class);

  private Resource resourceOpml;

  private PostCollector postCollector;

  public RssReader(@Value("classpath:rss/engineering_blogs.opml") Resource resourceOpml,
      PostCollector postCollector) {
    this.resourceOpml = resourceOpml;
    this.postCollector = postCollector;
  }

  public void consumeOpml() {

    WireFeedInput input = new WireFeedInput();
    List<Outline> outlines = null;
    try {
      Opml feed = (Opml) input.build(new InputSource(resourceOpml.getInputStream()));
      outlines = feed.getOutlines();
    } catch (IllegalArgumentException | IOException | FeedException exc) {
      logger.error("An error has been produced while opml was processed", exc);
    }

    List<Outline> outlinesToRead = CollectionUtils.isEmpty(outlines) ? Collections.emptyList()
        : outlines.get(0).getChildren();

    outlinesToRead
        .forEach(outlineForFeed -> {
          postCollector.execute(
              new CollectingPostParams(outlineForFeed.getXmlUrl(), outlineForFeed.getText()));
        });

  }
}
