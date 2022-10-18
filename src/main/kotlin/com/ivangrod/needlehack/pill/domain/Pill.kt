package com.ivangrod.needlehack.pill.domain

class Pill private constructor(
    id: PillId,
    title: Title,
    uri: Uri,
    creator: Author,
    origin: Feed,
    content: Content,
    collectedAt: CollectingDate,
    publishedAt: PublishingDate,
    topics: Set<Topic>
) {

    companion object {

        fun collect(
            title: Title,
            uri: Uri,
            creator: Author,
            origin: Feed,
            content: Content,
            publishedAt: PublishingDate,
            topics: Set<Topic>
        ): Pill = Pill(PillId.fromUri(uri), title, uri, creator, origin, content, CollectingDate(), publishedAt, topics)
    }
}
