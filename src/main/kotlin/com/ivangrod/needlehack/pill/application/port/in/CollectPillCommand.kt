package com.ivangrod.needlehack.pill.application.port.`in`

import com.ivangrod.needlehack.pill.domain.ChannelName
import com.ivangrod.needlehack.pill.domain.Feed
import com.ivangrod.needlehack.pill.domain.Uri

data class CollectPillCommand(private val feedUri: String, private val source: String) {
    fun createFeed(): Feed = Feed(Uri(feedUri), ChannelName(source))
}
