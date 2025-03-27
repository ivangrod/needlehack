package com.ivangrod.needlehack.pill.domain.event

import com.ivangrod.needlehack.pill.domain.Feed
import com.ivangrod.needlehack.pill.domain.Pill

interface DomainEvent

data class FeedCollected(val pills: List<Pill>) : DomainEvent

data class InvalidFeedRequested(val feed: Feed) : DomainEvent

