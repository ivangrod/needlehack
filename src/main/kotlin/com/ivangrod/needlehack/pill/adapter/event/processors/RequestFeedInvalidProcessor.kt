package com.ivangrod.needlehack.pill.adapter.event.processors

import com.ivangrod.needlehack.pill.adapter.service.FeedErrorsViewer
import com.ivangrod.needlehack.pill.domain.event.InvalidFeedRequested
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class RequestFeedInvalidProcessor {

    @Async
    @EventListener
    fun consume(event: InvalidFeedRequested) {
        FeedErrorsViewer.add(event.feed)
        log.debug(
            "Consuming invalid feed requested from feed [{}]",
            event.feed
        )
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(RequestFeedInvalidProcessor::class.java)
    }
}