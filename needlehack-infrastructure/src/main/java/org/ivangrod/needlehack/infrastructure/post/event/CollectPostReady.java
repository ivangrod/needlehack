package org.ivangrod.needlehack.infrastructure.post.event;

import java.time.Instant;
import org.ivangrod.needlehack.infrastructure.post.service.rss.RssReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CollectPostReady {

    private static final Logger logger = LoggerFactory.getLogger(CollectPostReady.class);

    private Boolean initCollectinPostProcess;

    private RssReader rssReader;

    public CollectPostReady(@Value("${needlehack.post.collect}") Boolean initCollectinPostProcess, RssReader rssReader) {
        this.initCollectinPostProcess = initCollectinPostProcess;
        this.rssReader = rssReader;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("#### Collecting process STARTED at [{}] ####", Instant.now());
        rssReader.consumeOpml();
        logger.info("#### Collecting process ENDED at [{}] ####", Instant.now());
    }
}
