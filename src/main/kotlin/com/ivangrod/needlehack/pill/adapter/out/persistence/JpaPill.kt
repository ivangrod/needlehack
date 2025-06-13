package com.ivangrod.needlehack.pill.adapter.out.persistence

import com.ivangrod.needlehack.pill.domain.*
import com.ivangrod.needlehack.pill.domain.Feed
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "pill")
class JpaPill(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

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

    @Column(name = "embedding", columnDefinition = "vector(768)")
    @JdbcTypeCode(SqlTypes.VECTOR)
    val embedding: DoubleArray? = null,
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
                publishedAt = pillAggregate.publishedAt.value,
                embedding = pillAggregate.embedding?.value
            )
        }
    }
}

fun JpaPill.toDomain() =
    Pill(
        uri = Uri.of(this.uri.orEmpty()),
        title = Title(this.title.orEmpty()),
        author = Author(this.author.orEmpty()),
        origin = Feed(Uri.of(this.originUri.orEmpty()), ChannelName(this.channel.orEmpty())),
        content = Content(this.content.orEmpty()),
        collectedAt = CollectingDate(this.collectedAt),
        publishedAt = PublishingDate(this.publishedAt),
        topics = setOf(),
        embedding = Embedding(this.embedding.orEmpty())
    )

private fun DoubleArray?.orEmpty(): DoubleArray = this ?: DoubleArray(0)


fun JpaPill.toRecommendation() =
    Recommendation(
        uri = Uri.of(this.uri.orEmpty()),
        title = Title(this.title.orEmpty()),
        origin = Feed(Uri.of(this.originUri.orEmpty()), ChannelName(this.channel.orEmpty())),
    )