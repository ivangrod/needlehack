package com.ivangrod.needlehack.pill.domain

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
