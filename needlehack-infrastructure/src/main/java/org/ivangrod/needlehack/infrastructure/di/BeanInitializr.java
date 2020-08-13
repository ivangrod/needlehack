package org.ivangrod.needlehack.infrastructure.di;

import org.ivangrod.needlehack.application.post.create.PostCreator;
import org.ivangrod.needlehack.domain.post.PostRepository;
import org.ivangrod.needlehack.domain.shared.event.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializr {

    @Bean
    public PostCreator createFeedItem(PostRepository elasticsearchPostRepository, EventBus rabbitMqEventBus) {
        return new PostCreator(elasticsearchPostRepository, rabbitMqEventBus);
    }
}
