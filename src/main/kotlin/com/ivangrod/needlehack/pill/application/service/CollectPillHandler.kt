package com.ivangrod.needlehack.pill.application.service

import com.ivangrod.needlehack.pill.application.port.`in`.CollectPill
import com.ivangrod.needlehack.pill.application.port.`in`.CollectPillCommand
import com.ivangrod.needlehack.pill.application.port.out.FeedExtractor
import com.ivangrod.needlehack.pill.application.port.out.Pills

class CollectPillHandler(private val feedExtractor: FeedExtractor, private val repository: Pills) : CollectPill {

    override fun collect(command: CollectPillCommand) =
        command.createFeed()
            .let { feed -> feedExtractor.extract(feed) }
            .forEach { pill -> repository.save(pill) }
}
