package com.ivangrod.needlehack.pill.adapter.event.processors

import com.ivangrod.needlehack.pill.domain.event.FeedCollected
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class CollectFeedProcessor {

    @Async
    @EventListener
    fun consume(event: FeedCollected) {
        log.debug(
            "Consuming collecting feed event from feed [{}] ...",
            event.pills.first().origin.channel.value
        )
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(CollectFeedProcessor::class.java)
    }
}