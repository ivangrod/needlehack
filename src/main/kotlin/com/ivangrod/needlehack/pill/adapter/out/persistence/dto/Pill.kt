package com.ivangrod.needlehack.pill.adapter.out.persistence.dto

import com.ivangrod.needlehack.pill.domain.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import com.ivangrod.needlehack.pill.domain.Feed as FeedVO
import com.ivangrod.needlehack.pill.domain.Pill as PillAggregate

@Document("pills")
data class Pill(
    @Id
    val id: String,
    val title: String? = null,
    val uri: String? = null,
    val author: String? = null,
    val origin: Feed,
    val content: String? = null,
    val collectedAt: LocalDateTime,
    val publishedAt: LocalDateTime,
    val topics: Set<String> = setOf()
) {
    companion object {
        fun fromDomain(pillAggregate: PillAggregate): Pill {
            return Pill(
                id = pillAggregate.id.value,
                title = pillAggregate.title.value,
                uri = pillAggregate.uri.value,
                author = pillAggregate.author.value,
                origin = Feed(pillAggregate.origin.uri.value, pillAggregate.origin.channel.value),
                content = pillAggregate.content.value,
                collectedAt = pillAggregate.collectedAt.value,
                publishedAt = pillAggregate.publishedAt.value,
                topics = pillAggregate.topics.map { it.value }.toSet()
            )
        }
    }
}

data class Feed(val uri: String? = null, val channel: String? = null)

fun Pill.toDomain() =
    PillAggregate(
        id = PillId(this.id),
        title = Title(this.title.orEmpty()),
        uri = Uri(this.uri.orEmpty()),
        author = Author(this.author.orEmpty()),
        origin = FeedVO(Uri(this.origin.uri.orEmpty()), ChannelName(this.origin.channel.orEmpty())),
        content = Content(this.content.orEmpty()),
        collectedAt = CollectingDate(this.collectedAt),
        publishedAt = PublishingDate(this.publishedAt),
        topics = this.topics.map { Topic(it) }.toSet()
    )
