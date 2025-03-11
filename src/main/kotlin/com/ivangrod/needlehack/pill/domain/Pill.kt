package com.ivangrod.needlehack.pill.domain

import java.util.stream.Collectors


data class Pill(
    val uri: Uri,
    val id: PillId = PillId.fromUri(uri),
    val title: Title,
    val author: Author,
    val origin: Feed,
    val content: Content,
    val collectedAt: CollectingDate,
    val publishedAt: PublishingDate,
    val topics: Set<Topic>
) {

    fun toPrimitives(): HashMap<String?, Any?> {
        return object : HashMap<String?, Any?>() {

            @java.io.Serial
            private val serialVersionUID: Long = 0

            init {
                put("title", title.value)
                put("uri", uri.value)
                put("creator", author.value)
                put("origin", origin.channel.value)
                put("feedUri", origin.uri.value)
                put("content", content.value)
                put("publication_date", publishedAt.publicationDateFormatted())
                put("topics", topics.stream().map(Topic::value).collect(Collectors.joining(","))
                )
            }
        }
    }

    companion object {

        fun collect(
            title: Title,
            uri: Uri,
            author: Author,
            origin: Feed,
            content: Content,
            publishedAt: PublishingDate,
            topics: Set<Topic>
        ): Pill = Pill(uri, PillId.fromUri(uri), title, author, origin, content, CollectingDate(), publishedAt, topics)
    }
}
