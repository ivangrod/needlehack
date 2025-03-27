package com.ivangrod.needlehack.pill.adapter.service

import com.ivangrod.needlehack.pill.domain.Feed
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.CopyOnWriteArrayList

object FeedErrorsViewer {

    private val log: Logger = LoggerFactory.getLogger(FeedErrorsViewer::class.java)

    private val feedsWithErrors = CopyOnWriteArrayList<Feed>()

    fun add(feed: Feed) {
        feedsWithErrors.add(feed)
    }

    fun show() {
        log.info(
            "Feeds with error at collecting process [${feedsWithErrors.joinToString(transform = { feed -> feed.channel.value })}]"
        )
    }

}