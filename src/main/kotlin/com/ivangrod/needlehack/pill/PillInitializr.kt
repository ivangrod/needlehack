package com.ivangrod.needlehack.pill

import com.ivangrod.needlehack.pill.adapter.out.persistence.MongoDBPills
import com.ivangrod.needlehack.pill.adapter.out.rss.RomeFeedExtractor
import com.ivangrod.needlehack.pill.adapter.out.text.JsoupProcessor
import com.ivangrod.needlehack.pill.application.port.out.FeedExtractor
import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.application.service.CollectPillHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PillInitializr {

    @Bean
    fun feedExtractor(): FeedExtractor = RomeFeedExtractor(JsoupProcessor())

    @Bean
    fun pills(): Pills = MongoDBPills()

    @Bean
    fun collectPill(feedExtractor: FeedExtractor, pills: Pills): CollectPillHandler =
        CollectPillHandler(feedExtractor, pills)

}
