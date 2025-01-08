package com.ivangrod.needlehack.pill.application.port.out

import com.ivangrod.needlehack.pill.domain.Feed
import com.ivangrod.needlehack.pill.domain.Pill

fun interface FeedExtractor {
    fun extract(feed: Feed): List<Pill>
}
