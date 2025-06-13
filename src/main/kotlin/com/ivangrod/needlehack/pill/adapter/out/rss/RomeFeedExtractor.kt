package com.ivangrod.needlehack.pill.adapter.out.rss

import com.ivangrod.needlehack.pill.application.port.out.ContentProcessor
import com.ivangrod.needlehack.pill.application.port.out.FeedExtractor
import com.ivangrod.needlehack.pill.domain.*
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URL

class RomeFeedExtractor(private val contentProcessor: ContentProcessor) : FeedExtractor {

    override fun extract(feed: Feed): List<Pill> {
        return try {
            SyndFeedInput()
                .build(XmlReader(URL(feed.uri.value)))
                .entries
                .map { entry ->
                    Pill
                        .collect(
                            Title(entry.title),
                            Uri.of(entry.link),
                            Author(entry.author),
                            feed,
                            extractContent(entry),
                            PublishingDate(entry.publishedDate ?: entry.updatedDate),
                            extractTopics(entry)
                        )
                }.toList()
        } catch (exception: Exception) {
            listOf()
        }
    }

    private fun extractContent(entryFromFeed: SyndEntry): Content {
        val strBuilder = StringBuilder()
        return if (entryFromFeed.contents != null)
            entryFromFeed.contents?.forEach { strBuilder.append(it.value) }
                .let { contentProcessor.execute(strBuilder.toString()) }
        else contentProcessor.execute(entryFromFeed.description?.value ?: "")
    }

    private fun extractTopics(entryFromFeed: SyndEntry): Set<Topic> =
        entryFromFeed.categories?.map { Topic(it.name) }?.toSet().orEmpty()

    companion object {
        private val log: Logger = LoggerFactory.getLogger(RomeFeedExtractor::class.java)
    }
}
