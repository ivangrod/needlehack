package org.ivangrod.needlehack.infrastructure.post.di;

import org.ivangrod.needlehack.application.post.collect.PostCollector;
import org.ivangrod.needlehack.application.post.create.PostCreator;
import org.ivangrod.needlehack.application.post.search.PostSearcher;
import org.ivangrod.needlehack.domain.post.FeedExtractor;
import org.ivangrod.needlehack.domain.post.PostRepository;
import org.ivangrod.needlehack.domain.shared.event.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializr {

    @Bean
    public PostCreator createPost(PostRepository elasticsearchPostRepository,
                                  EventBus springApplicationEventBus) {
        return new PostCreator(elasticsearchPostRepository, springApplicationEventBus);
    }

    @Bean
    public PostSearcher searchPost(PostRepository elasticsearchPostRepository,
                                   EventBus springApplicationEventBus) {
        return new PostSearcher(elasticsearchPostRepository, springApplicationEventBus);
    }

    @Bean
    public PostCollector collectPost(FeedExtractor feedExtractor, EventBus eventBus) {
        return new PostCollector(feedExtractor, eventBus);
    }
}
