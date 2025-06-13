package com.ivangrod.needlehack.pill.adapter.out.resources

import com.ivangrod.needlehack.pill.application.port.out.Feeds
import com.ivangrod.needlehack.pill.domain.ChannelName
import com.ivangrod.needlehack.pill.domain.Feed
import com.ivangrod.needlehack.pill.domain.Uri
import com.rometools.opml.feed.opml.Opml
import com.rometools.rome.io.WireFeedInput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.xml.sax.InputSource

class OpmlFeeds(resourceOpml: Resource) : Feeds {

    private val feeds: Set<Feed>

    override fun byChannelName(channelName: ChannelName): Feed? = feeds.find { feed -> feed.channel == channelName }

    override fun all(): Set<Feed> = feeds

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(OpmlFeeds::class.java)
    }

    init {
        this.feeds =
            (WireFeedInput().build(InputSource(resourceOpml.inputStream)) as Opml).outlines[0]?.children?.map {
                Feed(Uri.of(it.xmlUrl), ChannelName(it.text))
            }?.toSet().orEmpty()
    }
}
