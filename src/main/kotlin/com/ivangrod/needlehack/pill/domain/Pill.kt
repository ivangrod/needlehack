package com.ivangrod.needlehack.pill.domain

data class Pill(
    val id: PillId,
    val title: Title,
    val uri: Uri,
    val author: Author,
    val origin: Feed,
    val content: Content,
    val collectedAt: CollectingDate,
    val publishedAt: PublishingDate,
    val topics: Set<Topic>
) {

    companion object {

        fun collect(
            title: Title,
            uri: Uri,
            author: Author,
            origin: Feed,
            content: Content,
            publishedAt: PublishingDate,
            topics: Set<Topic>
        ): Pill = Pill(PillId.fromUri(uri), title, uri, author, origin, content, CollectingDate(), publishedAt, topics)
    }
}
