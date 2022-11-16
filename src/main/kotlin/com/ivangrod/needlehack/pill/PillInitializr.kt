package com.ivangrod.needlehack.pill

import com.ivangrod.needlehack.pill.adapter.out.persistence.JpaPillRepository
import com.ivangrod.needlehack.pill.adapter.out.persistence.JpaPills
import com.ivangrod.needlehack.pill.adapter.out.persistence.MongoDBPillRepository
import com.ivangrod.needlehack.pill.adapter.out.persistence.MongoDBPills
import com.ivangrod.needlehack.pill.adapter.out.resources.OpmlFeeds
import com.ivangrod.needlehack.pill.adapter.out.rss.RomeFeedExtractor
import com.ivangrod.needlehack.pill.adapter.out.text.JsoupProcessor
import com.ivangrod.needlehack.pill.application.port.out.FeedExtractor
import com.ivangrod.needlehack.pill.application.port.out.Feeds
import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.application.service.CollectPillHandler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class PillInitializr {

    @Bean
    fun feedExtractor(): FeedExtractor = RomeFeedExtractor(JsoupProcessor())

    @Bean("mongoPills")
    fun pills(repository: MongoDBPillRepository): Pills = MongoDBPills(repository)

    @Bean("jpaPills")
    fun pills(repository: JpaPillRepository): Pills = JpaPills(repository)

    @Bean
    fun feeds(@Value("classpath:rss/engineering_blogs.opml") resourceOpml: Resource): Feeds = OpmlFeeds(resourceOpml)

    @Bean
    fun collectPill(feedExtractor: FeedExtractor, @Qualifier("jpaPills") pills: Pills): CollectPillHandler =
        CollectPillHandler(feedExtractor, pills)

}
