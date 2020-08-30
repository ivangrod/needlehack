package org.ivangrod.needlehack.infrastructure.post.scheduler;

import java.time.LocalDateTime;
import org.ivangrod.needlehack.domain.shared.format.DateFormatter;
import org.ivangrod.needlehack.infrastructure.post.service.rss.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CollectScheduler {

  private static final Logger logger = LoggerFactory.getLogger(CollectScheduler.class);

  private final static String EVERY_DAY_AT_6_AM_CRON = "0 0 6 * * ?";

  private RssReader rssReader;

  public CollectScheduler(RssReader rssReader) {
    this.rssReader = rssReader;
  }

  @Scheduled(cron = EVERY_DAY_AT_6_AM_CRON)
  public void cronJobScheduler() {
    logger.info("Init collecting process:: " + DateFormatter.dateToString(LocalDateTime.now()));
    rssReader.consumeOpml();
  }
}
