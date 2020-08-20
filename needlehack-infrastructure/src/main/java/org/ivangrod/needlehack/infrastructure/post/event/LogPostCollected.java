package org.ivangrod.needlehack.infrastructure.post.event;

import java.util.Collections;
import org.ivangrod.needlehack.application.post.create.CreatingPostParams;
import org.ivangrod.needlehack.application.post.create.PostCreator;
import org.ivangrod.needlehack.domain.post.event.PostCollected;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LogPostCollected {

    private static final Logger log = LoggerFactory.getLogger(LogPostCollected.class);

    private PostCreator postCreator;

    public LogPostCollected(PostCreator postCreator) {
        this.postCreator = postCreator;
    }

    @EventListener
    public void on(PostCollected event) {
        log.info(event.toString());

        // TODO Not invoke use case from here
        CreatingPostParams creatingPostParams = new CreatingPostParams(event.getTitle(),
                event.getUri(), "", "", event.getSource(), "",
                event.getPublicatedAt(), Collections.emptySet());
        postCreator.execute(creatingPostParams);
    }
}