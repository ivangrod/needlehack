package com.ivangrod.needlehack.pill.application.service

import com.ivangrod.needlehack.pill.application.port.`in`.CollectPill
import com.ivangrod.needlehack.pill.application.port.out.FeedExtractor
import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.domain.event.*

class CollectPillHandler(
    private val feedExtractor: FeedExtractor,
    private val repository: Pills,
    private val publisher: DomainEventPublisher
) : CollectPill {

    override fun collect(command: CollectPill.CollectPillCommand) {

        feedExtractor.extract(command.feed)
            .also { pills ->
                pills.takeIf { it.isNotEmpty() }
                    ?.let { publisher.publish(FeedCollected(it)) }
                    ?: publisher.publish(InvalidFeedRequested(command.feed))
            }
            .forEach(repository::save)
    }
}
