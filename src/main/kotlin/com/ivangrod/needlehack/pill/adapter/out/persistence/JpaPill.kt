package com.ivangrod.needlehack.pill.adapter.out.persistence

import com.ivangrod.needlehack.pill.domain.*
import com.ivangrod.needlehack.pill.domain.Feed
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "pill")
class JpaPill(

    @Column(nullable = true, length = 510)
    val title: String? = null,

    @Column(nullable = true)
    val uri: String? = null,

    @Column(nullable = true)
    val author: String? = null,

    @Column(nullable = true, name = "origin_uri")
    val originUri: String? = null,

    @Column(nullable = true)
    val channel: String? = null,

    @Column(nullable = true)
    @Lob
    val content: String? = null,

    @Column
    val collectedAt: LocalDateTime,

    @Column
    val publishedAt: LocalDateTime,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    companion object {
        fun fromDomain(pillAggregate: Pill): JpaPill {
            return JpaPill(
                title = pillAggregate.title.value,
                uri = pillAggregate.uri.value,
                author = pillAggregate.author.value,
                originUri = pillAggregate.origin.uri.value,
                channel = pillAggregate.origin.channel.value,
                content = pillAggregate.content.value,
                collectedAt = pillAggregate.collectedAt.value,
                publishedAt = pillAggregate.publishedAt.value
            )
        }
    }
}

fun JpaPill.toDomain() =
    Pill(
        uri = Uri(this.uri.orEmpty()),
        title = Title(this.title.orEmpty()),
        author = Author(this.author.orEmpty()),
        origin = Feed(Uri(this.originUri.orEmpty()), ChannelName(this.channel.orEmpty())),
        content = Content(this.content.orEmpty()),
        collectedAt = CollectingDate(this.collectedAt),
        publishedAt = PublishingDate(this.publishedAt),
        topics = setOf()
    )
