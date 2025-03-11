package com.ivangrod.needlehack.pill

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.rest_client.RestClientTransport
import com.ivangrod.needlehack.pill.adapter.out.persistence.*
import com.ivangrod.needlehack.pill.adapter.out.resources.OpmlFeeds
import com.ivangrod.needlehack.pill.adapter.out.rss.RomeFeedExtractor
import com.ivangrod.needlehack.pill.adapter.out.text.JsoupProcessor
import com.ivangrod.needlehack.pill.application.port.out.FeedExtractor
import com.ivangrod.needlehack.pill.application.port.out.Feeds
import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.application.service.CollectPillHandler
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import java.io.IOException


@Configuration
class PillInitializr {

    @Value("\${elasticsearch.host}")
    private lateinit var host: String

    @Value("\${elasticsearch.port}")
    private lateinit var port: String

    @Value("\${elasticsearch.schema}")
    private lateinit var schema: String

    @Bean
    fun feedExtractor(): FeedExtractor = RomeFeedExtractor(JsoupProcessor())

    @Bean("mongoPills")
    fun pills(repository: MongoDBPillRepository): Pills = MongoDBPills(repository)

    @Bean("jpaPills")
    fun pills(repository: JpaPillRepository): Pills = JpaPills(repository)

    @Bean
    fun feeds(@Value("classpath:rss/engineering_blogs.opml") resourceOpml: Resource): Feeds = OpmlFeeds(resourceOpml)

    @Bean
    fun collectPill(feedExtractor: FeedExtractor, @Qualifier("elasticPills") pills: Pills): CollectPillHandler =
        CollectPillHandler(feedExtractor, pills)

    @Bean
    @Throws(IOException::class)
    fun elasticsearchClient(): ElasticsearchClient {
        val restClient: RestClient = RestClient.builder(HttpHost(host, port.toInt(), schema)).build()
        val transport = RestClientTransport(restClient, JacksonJsonpMapper())
        return ElasticsearchClient(transport)
    }

    @Bean("elasticPills")
    fun pills(elasticsearchClient: ElasticsearchClient): ElasticSearchPills =
        ElasticSearchPills(elasticsearchClient, "needlehack")
}
