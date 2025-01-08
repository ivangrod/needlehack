package com.ivangrod.needlehack.pill.application.port.out

import com.ivangrod.needlehack.pill.domain.ChannelName
import com.ivangrod.needlehack.pill.domain.Feed

interface Feeds {

    fun byChannelName(channelName: ChannelName): Feed?

    fun all(): Set<Feed>
}
