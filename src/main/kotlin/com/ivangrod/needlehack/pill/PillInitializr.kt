package com.ivangrod.needlehack.pill

import com.ivangrod.needlehack.pill.adapter.out.http.NomicEmbeddingClient
import com.ivangrod.needlehack.pill.adapter.out.persistence.*
import com.ivangrod.needlehack.pill.adapter.out.resources.OpmlFeeds
import com.ivangrod.needlehack.pill.adapter.out.rss.RomeFeedExtractor
import com.ivangrod.needlehack.pill.adapter.out.text.JsoupProcessor
import com.ivangrod.needlehack.pill.adapter.service.PillEmbeddingFromAIModel
import com.ivangrod.needlehack.pill.adapter.service.QueryEmbeddingFromAIModel
import com.ivangrod.needlehack.pill.application.port.out.FeedExtractor
import com.ivangrod.needlehack.pill.application.port.out.Feeds
import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.application.port.out.Recommendations
import com.ivangrod.needlehack.pill.application.service.CollectPillHandler
import com.ivangrod.needlehack.pill.application.service.RecommendPillHandler
import com.ivangrod.needlehack.pill.domain.event.DomainEventPublisher
import com.ivangrod.needlehack.pill.domain.service.PillEmbedding
import com.ivangrod.needlehack.pill.domain.service.QueryEmbedding
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.web.client.RestTemplate


@Configuration
class PillInitializr {

    @Configuration
    class RestTemplateConfig {
        @Bean
        fun restTemplate(): RestTemplate = RestTemplate()
    }

    @Bean
    fun feedExtractor(): FeedExtractor = RomeFeedExtractor(JsoupProcessor())

    @Bean("jpaPills")
    fun pills(repository: JpaPillRepository): Pills = JpaPills(repository)

    @Bean("jpaRecommendations")
    fun recommendations(repository: JpaRecommendationRepository): Recommendations = JpaRecommendations(repository)

    @Bean
    fun feeds(@Value("classpath:rss/engineering_blogs.opml") resourceOpml: Resource): Feeds = OpmlFeeds(resourceOpml)

    @Bean
    fun embeddingClient(restTemplate: RestTemplate): NomicEmbeddingClient = NomicEmbeddingClient(restTemplate)

    @Bean
    fun pillEmbedding(embeddingClient: NomicEmbeddingClient): PillEmbedding = PillEmbeddingFromAIModel(embeddingClient)

    @Bean
    fun queryEmbedding(embeddingClient: NomicEmbeddingClient): QueryEmbedding =
        QueryEmbeddingFromAIModel(embeddingClient)

    @Bean
    fun collectPill(
        feedExtractor: FeedExtractor,
        pillEmbedding: PillEmbedding,
        @Qualifier("jpaPills") pills: Pills,
        publisher: DomainEventPublisher
    ): CollectPillHandler =
        CollectPillHandler(feedExtractor, pillEmbedding, pills, publisher)

    @Bean
    fun recommendPill(
        queryEmbedding: QueryEmbedding,
        @Qualifier("jpaRecommendations") recommendations: Recommendations,
    ): RecommendPillHandler =
        RecommendPillHandler(queryEmbedding, recommendations)
}