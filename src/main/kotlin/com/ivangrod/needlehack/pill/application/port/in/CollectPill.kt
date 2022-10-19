package com.ivangrod.needlehack.pill.application.port.`in`

import com.ivangrod.needlehack.pill.domain.Feed

interface CollectPill {
    fun collect(command: CollectPillCommand)

    data class CollectPillCommand(val feed: Feed)
}
