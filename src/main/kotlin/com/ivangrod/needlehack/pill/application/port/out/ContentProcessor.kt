package com.ivangrod.needlehack.pill.application.port.out

import com.ivangrod.needlehack.pill.domain.Content

fun interface ContentProcessor {
    fun execute(rawContent: String?): Content
}
