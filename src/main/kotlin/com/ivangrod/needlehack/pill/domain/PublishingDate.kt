package com.ivangrod.needlehack.pill.domain

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

data class PublishingDate(val value: LocalDateTime) {

    constructor(publicationAt: Date) : this(
        publicationAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    )

    fun publicationDateFormatted(): String {
        return value.format(TIME_FORMATTER)
    }

    companion object {
        private val TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        fun toPublishingDate(dateTime: String): PublishingDate {
            return PublishingDate(LocalDateTime.parse(dateTime, TIME_FORMATTER))
        }
    }
}
